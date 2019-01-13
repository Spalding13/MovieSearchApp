package com.example.android.movieapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {

    private final String LOG_TAG = ResultActivity.class.getName();
    private final String SEARCH_REQ_URL = "http://www.omdbapi.com/?apikey=thewdb&s=";
    private String SEARCH;
    private MovieAdapter mMovieAdapter;
    private ProgressBar mProgressBar;
    private TextView mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        GridView listView = findViewById(R.id.list);
        mEmptyView = findViewById(R.id.empty_view);
        mProgressBar = findViewById(R.id.progress_bar);
        setTitle("Results for: ");
        Intent intent = getIntent();
        SEARCH = intent.getStringExtra("SEARCH");
        setTitle("Results for: "+SEARCH);
        getLoaderManager().initLoader(0, null, this);

        mMovieAdapter = new MovieAdapter(this, new ArrayList<Movie>());
        listView.setEmptyView(mEmptyView);
        listView.setAdapter(mMovieAdapter);

    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, "TEST: onCreateLoader method called.");
        String uriSearch = SEARCH_REQ_URL + SEARCH;
        Log.i(LOG_TAG, "URI:" + uriSearch);
        Uri baseUri = Uri.parse(uriSearch);
        return new MovieLoader(this, baseUri.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        Log.i(LOG_TAG, "TEST: onLoadFinished method called.");
        mMovieAdapter.clear();
        if (movies != null && !movies.isEmpty()) {
            mMovieAdapter.addAll(movies);
        }
        mProgressBar.setVisibility(View.GONE);
        if(SEARCH.isEmpty()){
            mEmptyView.setText("No results found");
        }else {
            mEmptyView.setText("No results for: "+SEARCH);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        Log.i(LOG_TAG, "TEST: onLoaderReset() method called");
        mMovieAdapter.clear();
    }
}
