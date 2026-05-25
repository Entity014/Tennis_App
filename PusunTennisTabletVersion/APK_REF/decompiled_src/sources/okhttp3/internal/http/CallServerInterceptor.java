package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.StreamAllocation;
import okio.BufferedSink;
import okio.Okio;

/* loaded from: classes3.dex */
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        HttpStream httpStream = realInterceptorChain.httpStream();
        StreamAllocation streamAllocation = realInterceptorChain.streamAllocation();
        Request request = chain.request();
        long currentTimeMillis = System.currentTimeMillis();
        httpStream.writeRequestHeaders(request);
        if (HttpMethod.permitsRequestBody(request.method()) && request.body() != null) {
            BufferedSink buffer = Okio.buffer(httpStream.createRequestBody(request, request.body().contentLength()));
            request.body().writeTo(buffer);
            buffer.close();
        }
        httpStream.finishRequest();
        Response build = httpStream.readResponseHeaders().request(request).handshake(streamAllocation.connection().handshake()).sentRequestAtMillis(currentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        if (!this.forWebSocket || build.code() != 101) {
            build = build.newBuilder().body(httpStream.openResponseBody(build)).build();
        }
        if ("close".equalsIgnoreCase(build.request().header("Connection")) || "close".equalsIgnoreCase(build.header("Connection"))) {
            streamAllocation.noNewStreams();
        }
        int code = build.code();
        if ((code != 204 && code != 205) || build.body().contentLength() <= 0) {
            return build;
        }
        throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + build.body().contentLength());
    }
}
