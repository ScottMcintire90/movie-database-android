package com.epicodus.moviedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.queryButton) Button mQueryButton;
    @Bind(R.id.movieQuery) EditText mMovieQuery;
    @Bind(R.id.actorButton) Button mActorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mQueryButton.setOnClickListener(this);
        mActorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mQueryButton) {
            String query = mMovieQuery.getText().toString();
            Intent intent = new Intent(MainActivity.this, MovieResultsActivity.class);
            intent.putExtra("query", query);
            intent.putExtra("searchType", "movie");
            startActivity(intent);
        }
        if (v == mActorButton) {
            String query = mMovieQuery.getText().toString();
            Intent intent = new Intent(MainActivity.this, MovieResultsActivity.class);
            intent.putExtra("query", query);
            intent.putExtra("searchType", "person");
            startActivity(intent);
        }
    }
}
