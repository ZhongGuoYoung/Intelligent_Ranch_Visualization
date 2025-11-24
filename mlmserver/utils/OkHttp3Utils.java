package com.mlm.mlmserver.utils;

import okhttp3.*;

import java.io.IOException;

public class OkHttp3Utils {
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType Media_JSON = MediaType.parse("application/json");
    private static final String User_Agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36";

    public String get(String url) {
        Request request = new Request
                .Builder()
                .addHeader("User-Agent", User_Agent)
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println("地址：" + url);
            System.out.println("状态：" + response.code());
            return response.isSuccessful() ? response.body().string() : null;
        } catch (IOException e) {
            return null;
        }
    }

    public String post(String url, String jsonString) {
        RequestBody body = RequestBody.create(jsonString, Media_JSON);
        Request request = new Request
                .Builder()
                .addHeader("User-Agent", User_Agent)
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println("地址：" + url);
            System.out.println("状态：" + response.code());
            return response.isSuccessful() ? response.body().string() : null;
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        // System.out.println(get("https://data.istrongcloud.com/v2/data/complex/2025.json"));
    }
}
