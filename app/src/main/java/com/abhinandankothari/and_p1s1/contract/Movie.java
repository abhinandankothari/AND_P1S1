package com.abhinandankothari.and_p1s1.contract;

import android.os.Parcel;
import android.os.Parcelable;

import com.abhinandankothari.and_p1s1.Config;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;

public class Movie implements Parcelable {

    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";
    public static final String TAG = "com.abhinandankothari.and_p1s1.contract.Movie";

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String movieTitle;
    @SerializedName("poster_path")
    private String moviePosterThumbUrl;
    @SerializedName("overview")
    private String movieSynopsis;
    @SerializedName("vote_average")
    private String movieRating;
    @SerializedName("release_date")
    private String movieReleaseDate;

    public Movie(int id,String movieTitle, String moviePosterThumbUrl, String movieSynopsis, String movieRating, String movieReleaseDate) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.moviePosterThumbUrl = moviePosterThumbUrl;
        this.movieSynopsis = movieSynopsis;
        this.movieRating = movieRating;
        this.movieReleaseDate = movieReleaseDate;
    }

    public int getId() {
        return id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMoviePosterThumbUrl() {
        return IMAGE_BASE_URL + moviePosterThumbUrl + "?api_key="+ Config.API_KEY;
    }

    public String getMovieSynopsis() {
        return movieSynopsis;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.movieTitle);
        dest.writeString(this.moviePosterThumbUrl);
        dest.writeString(this.movieSynopsis);
        dest.writeString(this.movieRating);
        dest.writeString(this.movieReleaseDate);
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.movieTitle = in.readString();
        this.moviePosterThumbUrl = in.readString();
        this.movieSynopsis = in.readString();
        this.movieRating = in.readString();
        this.movieReleaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getYearFromReleaseDate() throws ParseException {
        if(this.getMovieReleaseDate() == null || this.getMovieReleaseDate().equals("")) return "";
        return (this.getMovieReleaseDate().substring(0,4)=="")?"":this.getMovieReleaseDate().substring(0,4);
    }
}
