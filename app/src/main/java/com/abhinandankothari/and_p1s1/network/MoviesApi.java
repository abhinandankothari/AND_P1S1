package com.abhinandankothari.and_p1s1.network;

import android.util.Log;

import com.abhinandankothari.and_p1s1.Config;
import com.abhinandankothari.and_p1s1.contract.Movie;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class MoviesApi {

    public static final String API_URL = "http://api.themoviedb.org/3/";
    public String sortCriteria;

    public List<Movie> ListofMovies(String sort) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Movies m = retrofit.create(Movies.class);
        if (sort.equals("Popularity")) {
            this.sortCriteria = "popularity.desc";
        } else
            this.sortCriteria = "vote_average.desc";
        Call<MoviesResponse> call = m.listOfMovies(Config.API_KEY, this.sortCriteria);
        try {
            MoviesResponse moviesResponse = call.execute().body();
            return moviesResponse.movies;
        } catch (IOException ex) {
            Log.e("ERROR", "Unable to get Data");
            return null;
        }
    }
}
