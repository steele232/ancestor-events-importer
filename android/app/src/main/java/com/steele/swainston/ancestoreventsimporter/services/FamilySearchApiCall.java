package com.steele.swainston.ancestoreventsimporter.services;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.steele.swainston.ancestoreventsimporter.schema.AuthResponse;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * To encapsulate some asynchronous calls to FamilySearch API
 */
public abstract class FamilySearchApiCall<T> extends AsyncTask<String, Void, T> {
    private static final String TAG = "FamilySearchApiCall";

    private static final String BASE_URL = "https://api-integ.familysearch.org/platform/tree/";
    private static final String ERR_CREDENTIAL_LENGTH = "Credentials should contain a username and password or a token";
    private static final String ERR_PATH_ARG = "Accepts only one path string.";

    private String[] credentials;
    private Class<T> typeOfT;
    private Callback<T> callback;

    public FamilySearchApiCall(String[] credentials, Class<T> typeOfT, Callback<T> callback) {
        if (credentials.length < 1 || credentials.length > 2) {
            throw new IllegalArgumentException(ERR_CREDENTIAL_LENGTH);
        }
        this.credentials = credentials;
        this.typeOfT = typeOfT;
        this.callback = callback;
    }

    @Override
    protected T doInBackground(String... path) {
        if (path.length != 1) {
            throw new IllegalArgumentException(ERR_PATH_ARG);
        }

        String token = getToken(credentials[0], credentials[1]);
        String url = String.format("%s%s", BASE_URL, path[0]);

        String response = HttpRequestHelper.get(url, token);
        Log.d(TAG, response);

        return new Gson().fromJson(response, typeOfT);
    }

    @Override
    protected void onPostExecute(T t) {
        callback.onComplete(t);
    }

    private String getToken(String username, String password) {
        String url = "https://identint.familysearch.org/cis-web/oauth2/v3/token";

        RequestBody formBody = new FormBody.Builder()
                .add("password", password)
                .add("grant_type", "password")
                .add("client_id", "a02f100000RMy4WAAT")
                .add("username", username)
                .build();

        String response = HttpRequestHelper.postForm(url, formBody);
        Log.d(TAG, response);

        AuthResponse auth = new Gson().fromJson(response, AuthResponse.class);
        return auth.getToken();
    }
}
