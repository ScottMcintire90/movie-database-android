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

    public ArrayList<Movie> mMovies =  new ArrayList<>();
    private MovieListAdapter mAdapter;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        String searchType = intent.getStringExtra("searchType");
        Log.v(TAG, searchType);
        getMovie(query, searchType);
    }


    private void getMovie(String query, String searchType) {
        final MovieService movieService = new MovieService();
        movieService.getMovie(query, searchType, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mMovies = movieService.processResults(response);

                for (int i = 0; i < mMovies.size(); i++) {
                    Log.v("response", "" + mMovies);
                    mMovies.get(i).getId();
                    Log.v("tag", "" + mMovies.get(i).getId());
                }
                MovieResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new MovieListAdapter(getApplicationContext(), mMovies);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MovieResultsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }


//    private void getActor(String query, String searchType) {
//        final MovieService movieService = new MovieService();
//        movieService.getMovie(query, searchType, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                mMovies = movieService.processActorResults(response);
//
//                for (int i = 0; i < mMovies.size(); i++) {
//                    Log.v("response", "" + mMovies);
//                    mMovies.get(i).getId();
//                    Log.v("tag", "" + mMovies.get(i).getId());
//                }
//                MovieResultsActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mAdapter = new MovieListAdapter(getApplicationContext(), mMovies);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MovieResultsActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//                    }
//                });
//            }
//        });
//    }
}

