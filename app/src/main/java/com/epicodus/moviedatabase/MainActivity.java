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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mQueryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mQueryButton) {
            String title = mMovieQuery.getText().toString();
            Intent intent = new Intent(MainActivity.this, MovieResultsActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("searchType", "movie");
            startActivity(intent);
        }
    }
}
