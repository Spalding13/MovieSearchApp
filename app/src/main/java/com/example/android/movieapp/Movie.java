package com.example.android.movieapp;

public class Movie {
    private String mTitle;
    private String mYear;
    private String mImage;

    public Movie (String title,String year,String url ){
        mTitle=title;
        mYear=year;
        mImage=url;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmYear() {
        return mYear;
    }

    public String getmImage(){
        return mImage;
    }
}
