package com.example.tennislockapp;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyHttpClient {

    private static final String TAG = "MyHttpClient";
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public interface HttpCallback {
        void onSuccess(String response);
        void onFailure(Exception e);
    }

    public void post(final String urlString, final String jsonPayload, final HttpCallback callback) {
        executorService.execute(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(urlString);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setConnectTimeout(2000);
                conn.setReadTimeout(3000);
                conn.setDoOutput(true);

                // Write JSON payload
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                int code = conn.getResponseCode();
                if (code >= 200 && code < 300) {
                    try (BufferedReader br = new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                        String resStr = response.toString();
                        mainHandler.post(() -> callback.onSuccess(resStr));
                    }
                } else {
                    // Try to read error stream
                    StringBuilder errorResponse = new StringBuilder();
                    if (conn.getErrorStream() != null) {
                        try (BufferedReader br = new BufferedReader(
                                new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                errorResponse.append(line.trim());
                            }
                        } catch (Exception ignored) {}
                    }
                    
                    String errMsg = errorResponse.length() > 0 ? errorResponse.toString() : "HTTP error code: " + code;
                    throw new Exception(errMsg);
                }
            } catch (final Exception e) {
                Log.e(TAG, "POST failed for URL: " + urlString, e);
                mainHandler.post(() -> callback.onFailure(e));
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        });
    }
}
