package com.epicodus.moviedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieResultsActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public ArrayList<String> titleArray =  new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String searchType = intent.getStringExtra("searchType");
        Log.v(TAG, title);
        getMovie(title, searchType);
    }

    private void getMovie(String title, String searchType) {
        final MovieService movieService = new MovieService();
        movieService.getMovie(title, searchType, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

