package com.epicodus.moviedatabase;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Date;

@Parcel
public class Movie {
    private String mTitle;
    private String mOverview;
    private String mCast;
    private String mYear;
    private String mImageUrl;
    private double mRating;
    private String mGenre;
    private static int mId;

    public Movie() {}


    public Movie(int id, String title, String overview, String cast, String imageUrl, String genre, double rating, String year){
        this.mTitle = title;
        this.mOverview = overview;
        this.mCast = cast;
        this.mImageUrl = "http://image.tmdb.org/t/p/w500" + imageUrl;
        this.mGenre = genre;
        this.mRating = rating;
        this.mYear = year;
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getYear(){
        return "("+mYear+")";
    }
    public String getOverview() {
        return mOverview;
    }
    public String getCast() {
        return mCast;
    }
    public String getImageUrl() {
        return mImageUrl;
    }
    public double getRating() {
        return mRating;
    }
    public String getGenre() {
        return mGenre;
    }
    public static int getId() {
        return mId;
    }

}