package okhttp3.internal.http;

import com.zhy.http.okhttp.OkHttpUtils;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.StreamAllocation;

/* loaded from: classes3.dex */
public final class RetryAndFollowUpInterceptor implements Interceptor {
    private static final int MAX_FOLLOW_UPS = 20;
    private volatile boolean canceled;
    private final OkHttpClient client;
    private boolean forWebSocket;
    private StreamAllocation streamAllocation;

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    public void cancel() {
        this.canceled = true;
        StreamAllocation streamAllocation = this.streamAllocation;
        if (streamAllocation != null) {
            streamAllocation.cancel();
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public OkHttpClient client() {
        return this.client;
    }

    public void setForWebSocket(boolean z) {
        this.forWebSocket = z;
    }

    public boolean isForWebSocket() {
        return this.forWebSocket;
    }

    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        this.streamAllocation = new StreamAllocation(this.client.connectionPool(), createAddress(request.url()));
        Response response = null;
        int i = 0;
        while (!this.canceled) {
            try {
                try {
                    Response proceed = ((RealInterceptorChain) chain).proceed(request, this.streamAllocation, null, null);
                    if (response != null) {
                        proceed = proceed.newBuilder().priorResponse(response.newBuilder().body(null).build()).build();
                    }
                    response = proceed;
                    request = followUpRequest(response);
                } catch (IOException e) {
                    if (!recover(e, false, request)) {
                        throw e;
                    }
                } catch (RouteException e2) {
                    if (!recover(e2.getLastConnectException(), true, request)) {
                        throw e2.getLastConnectException();
                    }
                }
                if (request == null) {
                    if (!this.forWebSocket) {
                        this.streamAllocation.release();
                    }
                    return response;
                }
                Util.closeQuietly(response.body());
                i++;
                if (i > 20) {
                    this.streamAllocation.release();
                    throw new ProtocolException("Too many follow-up requests: " + i);
                }
                if (request.body() instanceof UnrepeatableRequestBody) {
                    throw new HttpRetryException("Cannot retry streamed HTTP body", response.code());
                }
                if (!sameConnection(response, request.url())) {
                    this.streamAllocation.release();
                    this.streamAllocation = new StreamAllocation(this.client.connectionPool(), createAddress(request.url()));
                } else if (this.streamAllocation.stream() != null) {
                    throw new IllegalStateException("Closing the body of " + response + " didn't close its backing stream. Bad interceptor?");
                }
            } catch (Throwable th) {
                this.streamAllocation.streamFailed(null);
                this.streamAllocation.release();
                throw th;
            }
        }
        this.streamAllocation.release();
        throw new IOException("Canceled");
    }

    private Address createAddress(HttpUrl httpUrl) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifier;
        CertificatePinner certificatePinner;
        if (httpUrl.isHttps()) {
            sSLSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            certificatePinner = this.client.certificatePinner();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            certificatePinner = null;
        }
        return new Address(httpUrl.host(), httpUrl.port(), this.client.dns(), this.client.socketFactory(), sSLSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    private boolean recover(IOException iOException, boolean z, Request request) {
        this.streamAllocation.streamFailed(iOException);
        if (this.client.retryOnConnectionFailure()) {
            return (z || !(request.body() instanceof UnrepeatableRequestBody)) && isRecoverable(iOException, z) && this.streamAllocation.hasMoreRoutes();
        }
        return false;
    }

    private boolean isRecoverable(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && z : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private Request followUpRequest(Response response) throws IOException {
        String header;
        HttpUrl resolve;
        Proxy proxy;
        if (response == null) {
            throw new IllegalStateException();
        }
        RealConnection connection = this.streamAllocation.connection();
        Route route = connection != null ? connection.route() : null;
        int code = response.code();
        String method = response.request().method();
        if (code == 307 || code == 308) {
            if (!method.equals("GET") && !method.equals(OkHttpUtils.METHOD.HEAD)) {
                return null;
            }
        } else {
            if (code == 401) {
                return this.client.authenticator().authenticate(route, response);
            }
            if (code == 407) {
                if (route != null) {
                    proxy = route.proxy();
                } else {
                    proxy = this.client.proxy();
                }
                if (proxy.type() != Proxy.Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                return this.client.proxyAuthenticator().authenticate(route, response);
            }
            if (code == 408) {
                if (response.request().body() instanceof UnrepeatableRequestBody) {
                    return null;
                }
                return response.request();
            }
            switch (code) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        if (!this.client.followRedirects() || (header = response.header("Location")) == null || (resolve = response.request().url().resolve(header)) == null) {
            return null;
        }
        if (!resolve.scheme().equals(response.request().url().scheme()) && !this.client.followSslRedirects()) {
            return null;
        }
        Request.Builder newBuilder = response.request().newBuilder();
        if (HttpMethod.permitsRequestBody(method)) {
            if (HttpMethod.redirectsToGet(method)) {
                newBuilder.method("GET", null);
            } else {
                newBuilder.method(method, null);
            }
            newBuilder.removeHeader(com.google.common.net.HttpHeaders.TRANSFER_ENCODING);
            newBuilder.removeHeader("Content-Length");
            newBuilder.removeHeader("Content-Type");
        }
        if (!sameConnection(response, resolve)) {
            newBuilder.removeHeader("Authorization");
        }
        return newBuilder.url(resolve).build();
    }

    private boolean sameConnection(Response response, HttpUrl httpUrl) {
        HttpUrl url = response.request().url();
        return url.host().equals(httpUrl.host()) && url.port() == httpUrl.port() && url.scheme().equals(httpUrl.scheme());
    }
}
