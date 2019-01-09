package com.example.android.movieapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {

    private String LOG_TAG = MovieLoader.class.getName();
    private String mUrl;

    public MovieLoader(Context context, String url) {
        super(context);
        Log.i(LOG_TAG,"TEST: MovieLoader constructor called.");
        mUrl = url;
    }

    @Override
    public void onStartLoading() {
        Log.i(LOG_TAG,"TEST: MovieLoader method called.");
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        Log.i(LOG_TAG,"TEST: MovieLoader method called.");
        if (mUrl == null) {
            return null;
        }

        return Utility.getMovieData(mUrl);
    }
}

