package com.abhinandankothari.and_p1s1.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Movies {
    @GET("discover/movie")
    Call<MoviesResponse> listOfMovies(@Query("api_key") String api_key,
                                      @Query("sort_by") String sortCriteria);
}
