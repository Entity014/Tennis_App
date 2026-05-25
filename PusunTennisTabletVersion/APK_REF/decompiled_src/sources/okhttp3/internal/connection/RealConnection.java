package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionSpec;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.framed.ErrorCode;
import okhttp3.internal.framed.FramedConnection;
import okhttp3.internal.framed.FramedStream;
import okhttp3.internal.http.Http1xStream;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/* loaded from: classes3.dex */
public final class RealConnection extends FramedConnection.Listener implements Connection {
    public int allocationLimit;
    public volatile FramedConnection framedConnection;
    private Handshake handshake;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    public BufferedSink sink;
    public Socket socket;
    public BufferedSource source;
    public int successCount;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    public long idleAtNanos = Long.MAX_VALUE;

    public RealConnection(Route route) {
        this.route = route;
    }

    public void connect(int i, int i2, int i3, List<ConnectionSpec> list, boolean z) {
        if (this.protocol != null) {
            throw new IllegalStateException("already connected");
        }
        ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(list);
        if (this.route.address().sslSocketFactory() == null) {
            if (!list.contains(ConnectionSpec.CLEARTEXT)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String host = this.route.address().url().host();
            if (!Platform.get().isCleartextTrafficPermitted(host)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication to " + host + " not permitted by network security policy"));
            }
        }
        RouteException routeException = null;
        while (this.protocol == null) {
            try {
                if (this.route.requiresTunnel()) {
                    buildTunneledConnection(i, i2, i3, connectionSpecSelector);
                } else {
                    buildConnection(i, i2, i3, connectionSpecSelector);
                }
            } catch (IOException e) {
                Util.closeQuietly(this.socket);
                Util.closeQuietly(this.rawSocket);
                this.socket = null;
                this.rawSocket = null;
                this.source = null;
                this.sink = null;
                this.handshake = null;
                this.protocol = null;
                if (routeException == null) {
                    routeException = new RouteException(e);
                } else {
                    routeException.addConnectException(e);
                }
                if (z) {
                    if (!connectionSpecSelector.connectionFailed(e)) {
                        throw routeException;
                    }
                } else {
                    throw routeException;
                }
            }
        }
    }

    private void buildTunneledConnection(int i, int i2, int i3, ConnectionSpecSelector connectionSpecSelector) throws IOException {
        Request createTunnelRequest = createTunnelRequest();
        HttpUrl url = createTunnelRequest.url();
        int i4 = 0;
        while (true) {
            i4++;
            if (i4 > 21) {
                throw new ProtocolException("Too many tunnel connections attempted: 21");
            }
            connectSocket(i, i2);
            createTunnelRequest = createTunnel(i2, i3, createTunnelRequest, url);
            if (createTunnelRequest != null) {
                Util.closeQuietly(this.rawSocket);
                this.rawSocket = null;
                this.sink = null;
                this.source = null;
            } else {
                establishProtocol(i2, i3, connectionSpecSelector);
                return;
            }
        }
    }

    private void buildConnection(int i, int i2, int i3, ConnectionSpecSelector connectionSpecSelector) throws IOException {
        connectSocket(i, i2);
        establishProtocol(i2, i3, connectionSpecSelector);
    }

    private void connectSocket(int i, int i2) throws IOException {
        Proxy proxy = this.route.proxy();
        Socket createSocket = (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) ? this.route.address().socketFactory().createSocket() : new Socket(proxy);
        this.rawSocket = createSocket;
        createSocket.setSoTimeout(i2);
        try {
            Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), i);
            this.source = Okio.buffer(Okio.source(this.rawSocket));
            this.sink = Okio.buffer(Okio.sink(this.rawSocket));
        } catch (ConnectException unused) {
            throw new ConnectException("Failed to connect to " + this.route.socketAddress());
        }
    }

    private void establishProtocol(int i, int i2, ConnectionSpecSelector connectionSpecSelector) throws IOException {
        if (this.route.address().sslSocketFactory() != null) {
            connectTls(i, i2, connectionSpecSelector);
        } else {
            this.protocol = Protocol.HTTP_1_1;
            this.socket = this.rawSocket;
        }
        if (this.protocol == Protocol.SPDY_3 || this.protocol == Protocol.HTTP_2) {
            this.socket.setSoTimeout(0);
            FramedConnection build = new FramedConnection.Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).protocol(this.protocol).listener(this).build();
            build.start();
            this.allocationLimit = build.maxConcurrentStreams();
            this.framedConnection = build;
            return;
        }
        this.allocationLimit = 1;
    }

    private void connectTls(int i, int i2, ConnectionSpecSelector connectionSpecSelector) throws IOException {
        SSLSocket sSLSocket;
        Address address = this.route.address();
        SSLSocket sSLSocket2 = null;
        try {
            try {
                sSLSocket = (SSLSocket) address.sslSocketFactory().createSocket(this.rawSocket, address.url().host(), address.url().port(), true);
            } catch (AssertionError e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            ConnectionSpec configureSecureSocket = connectionSpecSelector.configureSecureSocket(sSLSocket);
            if (configureSecureSocket.supportsTlsExtensions()) {
                Platform.get().configureTlsExtensions(sSLSocket, address.url().host(), address.protocols());
            }
            sSLSocket.startHandshake();
            Handshake handshake = Handshake.get(sSLSocket.getSession());
            if (!address.hostnameVerifier().verify(address.url().host(), sSLSocket.getSession())) {
                X509Certificate x509Certificate = (X509Certificate) handshake.peerCertificates().get(0);
                throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified:\n    certificate: " + CertificatePinner.pin(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + OkHostnameVerifier.allSubjectAltNames(x509Certificate));
            }
            address.certificatePinner().check(address.url().host(), handshake.peerCertificates());
            String selectedProtocol = configureSecureSocket.supportsTlsExtensions() ? Platform.get().getSelectedProtocol(sSLSocket) : null;
            this.socket = sSLSocket;
            this.source = Okio.buffer(Okio.source(sSLSocket));
            this.sink = Okio.buffer(Okio.sink(this.socket));
            this.handshake = handshake;
            this.protocol = selectedProtocol != null ? Protocol.get(selectedProtocol) : Protocol.HTTP_1_1;
            if (sSLSocket != null) {
                Platform.get().afterHandshake(sSLSocket);
            }
        } catch (AssertionError e2) {
            e = e2;
            if (!Util.isAndroidGetsocknameError(e)) {
                throw e;
            }
            throw new IOException(e);
        } catch (Throwable th2) {
            th = th2;
            sSLSocket2 = sSLSocket;
            if (sSLSocket2 != null) {
                Platform.get().afterHandshake(sSLSocket2);
            }
            Util.closeQuietly((Socket) sSLSocket2);
            throw th;
        }
    }

    private Request createTunnel(int i, int i2, Request request, HttpUrl httpUrl) throws IOException {
        String str = "CONNECT " + Util.hostHeader(httpUrl, true) + " HTTP/1.1";
        while (true) {
            Http1xStream http1xStream = new Http1xStream(null, null, this.source, this.sink);
            this.source.timeout().timeout(i, TimeUnit.MILLISECONDS);
            this.sink.timeout().timeout(i2, TimeUnit.MILLISECONDS);
            http1xStream.writeRequest(request.headers(), str);
            http1xStream.finishRequest();
            Response build = http1xStream.readResponse().request(request).build();
            long contentLength = HttpHeaders.contentLength(build);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source newFixedLengthSource = http1xStream.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            newFixedLengthSource.close();
            int code = build.code();
            if (code == 200) {
                if (this.source.buffer().exhausted() && this.sink.buffer().exhausted()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (code == 407) {
                Request authenticate = this.route.address().proxyAuthenticator().authenticate(this.route, build);
                if (authenticate == null) {
                    throw new IOException("Failed to authenticate with proxy");
                }
                if ("close".equalsIgnoreCase(build.header("Connection"))) {
                    return authenticate;
                }
                request = authenticate;
            } else {
                throw new IOException("Unexpected response code for CONNECT: " + build.code());
            }
        }
    }

    private Request createTunnelRequest() {
        return new Request.Builder().url(this.route.address().url()).header(com.google.common.net.HttpHeaders.HOST, Util.hostHeader(this.route.address().url(), true)).header("Proxy-Connection", com.google.common.net.HttpHeaders.KEEP_ALIVE).header("User-Agent", Version.userAgent()).build();
    }

    @Override // okhttp3.Connection
    public Route route() {
        return this.route;
    }

    public void cancel() {
        Util.closeQuietly(this.rawSocket);
    }

    @Override // okhttp3.Connection
    public Socket socket() {
        return this.socket;
    }

    public boolean isHealthy(boolean z) {
        if (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) {
            return false;
        }
        if (this.framedConnection == null && z) {
            try {
                int soTimeout = this.socket.getSoTimeout();
                try {
                    this.socket.setSoTimeout(1);
                    return !this.source.exhausted();
                } finally {
                    this.socket.setSoTimeout(soTimeout);
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    @Override // okhttp3.internal.framed.FramedConnection.Listener
    public void onStream(FramedStream framedStream) throws IOException {
        framedStream.close(ErrorCode.REFUSED_STREAM);
    }

    @Override // okhttp3.internal.framed.FramedConnection.Listener
    public void onSettings(FramedConnection framedConnection) {
        this.allocationLimit = framedConnection.maxConcurrentStreams();
    }

    @Override // okhttp3.Connection
    public Handshake handshake() {
        return this.handshake;
    }

    public boolean isMultiplexed() {
        return this.framedConnection != null;
    }

    @Override // okhttp3.Connection
    public Protocol protocol() {
        if (this.framedConnection == null) {
            Protocol protocol = this.protocol;
            return protocol != null ? protocol : Protocol.HTTP_1_1;
        }
        return this.framedConnection.getProtocol();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Connection{");
        sb.append(this.route.address().url().host());
        sb.append(":");
        sb.append(this.route.address().url().port());
        sb.append(", proxy=");
        sb.append(this.route.proxy());
        sb.append(" hostAddress=");
        sb.append(this.route.socketAddress());
        sb.append(" cipherSuite=");
        Handshake handshake = this.handshake;
        sb.append(handshake != null ? handshake.cipherSuite() : "none");
        sb.append(" protocol=");
        sb.append(this.protocol);
        sb.append('}');
        return sb.toString();
    }
}
