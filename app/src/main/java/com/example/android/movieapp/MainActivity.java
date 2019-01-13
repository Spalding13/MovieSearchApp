package com.example.android.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getName();
    private String mMovieSearch;
    private EditText mSearchBar;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchBar = findViewById(R.id.search_bar);
        mButton = findViewById(R.id.search_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMovieSearch = mSearchBar.getText().toString();
                startIntent();
            }
        });

        mSearchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mMovieSearch = mSearchBar.getText().toString();
                    startIntent();
                    return true;
                }
                return false;
            }
        });
    }
    public void startIntent(){
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("SEARCH", mMovieSearch);
        startActivity(intent);
    }

}
