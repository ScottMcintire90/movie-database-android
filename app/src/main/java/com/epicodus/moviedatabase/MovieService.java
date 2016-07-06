package com.epicodus.moviedatabase;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MovieService {

    public static void getMovie(String title, String searchType, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addPathSegment(searchType);
        urlBuilder.addQueryParameter("query", title);
        urlBuilder.addQueryParameter("api_key", Constants.MOVIE_API_KEY);
        String url = urlBuilder.build().toString();
        Log.d("url", url);

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
