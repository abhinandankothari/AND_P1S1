package com.abhinandankothari.and_p1s1.network;

import com.abhinandankothari.and_p1s1.contract.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {
    @SerializedName("page")
    public int page;
    @SerializedName("total_results")
    public int total_results;
    @SerializedName("total_pages")
    public int total_pages;
    @SerializedName("results")
    public List<Movie> movies;
}
