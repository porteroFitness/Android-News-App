package com.example.newsapp.retrofitUtils;

import com.example.newsapp.model.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APINews {

    public static final String BASE_URL = "http://newsapi.org/v2/";

    @Headers({"Accept: application/json"})
    @GET("top-headlines")
    Call<Headlines> getHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

}