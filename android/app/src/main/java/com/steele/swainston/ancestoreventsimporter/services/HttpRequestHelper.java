package com.steele.swainston.ancestoreventsimporter.services;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author jonathansteele
 */
class HttpRequestHelper {

    static String get(String url, String token) {
        String responseString = "";
        try {
            Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/x-gedcomx-v1+json")
                .addHeader("Authorization", "Bearer " + token)
                .build();

            OkHttpClient client = new OkHttpClient();

            ResponseBody response = client.newCall(request).execute().body();
            if (response != null) {
                responseString = response.string();
            }
        } catch (IOException e) {
            Log.e("HttpRequestHelper", e.toString());
        }

        return responseString;
    }
        
    static String postForm(String url, RequestBody formBody) {
        String responseString = "";
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            OkHttpClient client = new OkHttpClient();

            ResponseBody response = client.newCall(request).execute().body();
            if (response != null) {
                responseString = response.string();
            }
        }
        catch (IOException e) {
            Log.e("HttpRequestHelper", e.toString());
        }

        return responseString;
    }
    
    
}
