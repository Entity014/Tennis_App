package com.zhy.http.okhttp.callback;

import com.zhy.http.okhttp.OkHttpUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Response;

/* loaded from: classes3.dex */
public abstract class FileCallBack extends Callback<File> {
    private String destFileDir;
    private String destFileName;

    public FileCallBack(String str, String str2) {
        this.destFileDir = str;
        this.destFileName = str2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.zhy.http.okhttp.callback.Callback
    public File parseNetworkResponse(Response response, int i) throws Exception {
        return saveFile(response, i);
    }

    public File saveFile(Response response, final int i) throws IOException {
        FileOutputStream fileOutputStream;
        byte[] bArr = new byte[2048];
        InputStream inputStream = null;
        try {
            InputStream byteStream = response.body().byteStream();
            try {
                final long contentLength = response.body().contentLength();
                File file = new File(this.destFileDir);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, this.destFileName);
                fileOutputStream = new FileOutputStream(file2);
                long j = 0;
                while (true) {
                    try {
                        int read = byteStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        final long j2 = j + read;
                        fileOutputStream.write(bArr, 0, read);
                        OkHttpUtils.getInstance().getDelivery().execute(new Runnable() { // from class: com.zhy.http.okhttp.callback.FileCallBack.1
                            @Override // java.lang.Runnable
                            public void run() {
                                long j3 = contentLength;
                                FileCallBack.this.inProgress((j2 * 1.0f) / j3, j3, i);
                            }
                        });
                        j = j2;
                        bArr = bArr;
                    } catch (Throwable th) {
                        th = th;
                        inputStream = byteStream;
                        try {
                            response.body().close();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (IOException unused) {
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                throw th;
                            } catch (IOException unused2) {
                                throw th;
                            }
                        }
                        throw th;
                    }
                }
                fileOutputStream.flush();
                try {
                    response.body().close();
                    if (byteStream != null) {
                        byteStream.close();
                    }
                } catch (IOException unused3) {
                }
                try {
                    fileOutputStream.close();
                } catch (IOException unused4) {
                }
                return file2;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }
}
