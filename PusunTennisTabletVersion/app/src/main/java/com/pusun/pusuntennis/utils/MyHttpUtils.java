package com.pusun.pusuntennis.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* loaded from: classes3.dex */
public class MyHttpUtils {
    public static void logRequest(String str, HashMap<String, String> hashMap) {
        AppLog.e("请求：" + str + " params: " + CollectionUtil.getCollectionValue2(hashMap));
    }

    public static void sendGetRequest(String str, Callback callback) {
        new OkHttpClient().newCall(new Request.Builder().url(str).build()).enqueue(callback);
    }

    public static void getAsAync(String str, HashMap<String, String> hashMap, final MyJsonCallbalk myJsonCallbalk) {
        logRequest(str, hashMap);
        if (hashMap != null && hashMap.size() > 0) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                if (entry.getValue() == null) {
                    entry.setValue("");
                }
            }
        }
        RequestCall build = OkHttpUtils.get().url(str).params((Map<String, String>) hashMap).build();
        build.connTimeOut(60000L);
        build.readTimeOut(60000L);
        build.writeTimeOut(60000L);
        build.execute(new StringCallback() { // from class: com.pusun.pusuntennis.utils.MyHttpUtils.1
            @Override // com.zhy.http.okhttp.callback.Callback
            public void onError(Call call, Exception exc, int i) {
            }

            @Override // com.zhy.http.okhttp.callback.Callback
            public void onResponse(String str2, int i) {
                AppLog.e("返回:" + str2.toString());
                myJsonCallbalk.onResponse(str2);
            }
        });
    }

    public static void postAsAync(String str, HashMap<String, String> hashMap, MyJsonCallbalk myJsonCallbalk) {
        postFileAsAync(str, hashMap, null, "", myJsonCallbalk);
    }

    public static void postFileAsAync(String str, HashMap<String, String> hashMap, File file, String str2, final MyJsonCallbalk myJsonCallbalk) {
        logRequest(str, hashMap);
        if (hashMap != null && hashMap.size() > 0) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                if (entry.getValue() == null) {
                    entry.setValue("");
                }
            }
        }
        PostFormBuilder params = OkHttpUtils.post().url(str).params((Map<String, String>) hashMap);
        if (file != null) {
            params.addFile(str2, file.getName(), file);
        }
        RequestCall build = params.build();
        build.connTimeOut(60000L);
        build.readTimeOut(60000L);
        build.writeTimeOut(60000L);
        build.execute(new StringCallback() { // from class: com.pusun.pusuntennis.utils.MyHttpUtils.2
            @Override // com.zhy.http.okhttp.callback.Callback
            public void onError(Call call, Exception exc, int i) {
                exc.printStackTrace();
                myJsonCallbalk.onError(exc, 0);
            }

            @Override // com.zhy.http.okhttp.callback.Callback
            public void onResponse(String str3, int i) {
                AppLog.e("返回:" + str3);
                myJsonCallbalk.onResponse(str3);
            }

            @Override // com.zhy.http.okhttp.callback.Callback
            public void onBefore(Request request, int i) {
                myJsonCallbalk.onBefore();
            }

            @Override // com.zhy.http.okhttp.callback.Callback
            public void onAfter(int i) {
                myJsonCallbalk.onAfter();
            }
        });
    }

    public static void postMultiFilesAsAync(String str, HashMap<String, String> hashMap, File[] fileArr, String[] strArr, final MyJsonCallbalk myJsonCallbalk) {
        logRequest(str, hashMap);
        if (hashMap != null && hashMap.size() > 0) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                if (entry.getValue() == null) {
                    entry.setValue("");
                }
            }
        }
        PostFormBuilder params = OkHttpUtils.post().url(str).params((Map<String, String>) hashMap);
        if (fileArr != null && fileArr.length > 0) {
            for (int i = 0; i < fileArr.length; i++) {
                params.addFile(strArr[i], fileArr[i].getName(), fileArr[i]);
            }
        }
        RequestCall build = params.build();
        build.connTimeOut(60000L);
        build.readTimeOut(60000L);
        build.writeTimeOut(60000L);
        build.execute(new StringCallback() { // from class: com.pusun.pusuntennis.utils.MyHttpUtils.3
            @Override // com.zhy.http.okhttp.callback.Callback
            public void onBefore(Request request, int i2) {
                myJsonCallbalk.onBefore();
            }

            @Override // com.zhy.http.okhttp.callback.Callback
            public void onError(Call call, Exception exc, int i2) {
                exc.printStackTrace();
                myJsonCallbalk.onError(exc, 0);
            }

            @Override // com.zhy.http.okhttp.callback.Callback
            public void onResponse(String str2, int i2) {
                AppLog.e("返回:" + str2);
                myJsonCallbalk.onResponse(str2);
            }

            @Override // com.zhy.http.okhttp.callback.Callback
            public void onAfter(int i2) {
                myJsonCallbalk.onAfter();
            }
        });
    }
}
