package okhttp3;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;

/* loaded from: classes3.dex */
final class RealCall implements Call {
    private final OkHttpClient client;
    private boolean executed;
    Request originalRequest;
    private final RetryAndFollowUpInterceptor retryAndFollowUpInterceptor;

    protected RealCall(OkHttpClient okHttpClient, Request request) {
        this.client = okHttpClient;
        this.originalRequest = request;
        this.retryAndFollowUpInterceptor = new RetryAndFollowUpInterceptor(okHttpClient);
    }

    @Override // okhttp3.Call
    public Request request() {
        return this.originalRequest;
    }

    @Override // okhttp3.Call
    public Response execute() throws IOException {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        try {
            this.client.dispatcher().executed(this);
            Response responseWithInterceptorChain = getResponseWithInterceptorChain();
            if (responseWithInterceptorChain != null) {
                return responseWithInterceptorChain;
            }
            throw new IOException("Canceled");
        } finally {
            this.client.dispatcher().finished(this);
        }
    }

    synchronized void setForWebSocket() {
        if (this.executed) {
            throw new IllegalStateException("Already Executed");
        }
        this.retryAndFollowUpInterceptor.setForWebSocket(true);
    }

    @Override // okhttp3.Call
    public void enqueue(Callback callback) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        this.client.dispatcher().enqueue(new AsyncCall(callback));
    }

    @Override // okhttp3.Call
    public void cancel() {
        this.retryAndFollowUpInterceptor.cancel();
    }

    @Override // okhttp3.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    @Override // okhttp3.Call
    public boolean isCanceled() {
        return this.retryAndFollowUpInterceptor.isCanceled();
    }

    StreamAllocation streamAllocation() {
        return this.retryAndFollowUpInterceptor.streamAllocation();
    }

    final class AsyncCall extends NamedRunnable {
        private final Callback responseCallback;

        private AsyncCall(Callback callback) {
            super("OkHttp %s", RealCall.this.redactedUrl().toString());
            this.responseCallback = callback;
        }

        String host() {
            return RealCall.this.originalRequest.url().host();
        }

        Request request() {
            return RealCall.this.originalRequest;
        }

        RealCall get() {
            return RealCall.this;
        }

        @Override // okhttp3.internal.NamedRunnable
        protected void execute() {
            IOException e;
            boolean z;
            Response responseWithInterceptorChain;
            try {
                try {
                    responseWithInterceptorChain = RealCall.this.getResponseWithInterceptorChain();
                    z = true;
                } catch (IOException e2) {
                    e = e2;
                    z = false;
                }
                try {
                    if (RealCall.this.retryAndFollowUpInterceptor.isCanceled()) {
                        this.responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
                    } else {
                        this.responseCallback.onResponse(RealCall.this, responseWithInterceptorChain);
                    }
                } catch (IOException e3) {
                    e = e3;
                    if (z) {
                        Platform.get().log(4, "Callback failure for " + RealCall.this.toLoggableString(), e);
                    } else {
                        this.responseCallback.onFailure(RealCall.this, e);
                    }
                }
            } finally {
                RealCall.this.client.dispatcher().finished(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String toLoggableString() {
        return (this.retryAndFollowUpInterceptor.isCanceled() ? "canceled call" : NotificationCompat.CATEGORY_CALL) + " to " + redactedUrl();
    }

    HttpUrl redactedUrl() {
        return this.originalRequest.url().resolve("/...");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Response getResponseWithInterceptorChain() throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.client.interceptors());
        arrayList.add(this.retryAndFollowUpInterceptor);
        arrayList.add(new BridgeInterceptor(this.client.cookieJar()));
        arrayList.add(new CacheInterceptor(this.client.internalCache()));
        arrayList.add(new ConnectInterceptor(this.client));
        if (!this.retryAndFollowUpInterceptor.isForWebSocket()) {
            arrayList.addAll(this.client.networkInterceptors());
        }
        arrayList.add(new CallServerInterceptor(this.retryAndFollowUpInterceptor.isForWebSocket()));
        return new RealInterceptorChain(arrayList, null, null, null, 0, this.originalRequest).proceed(this.originalRequest);
    }
}
