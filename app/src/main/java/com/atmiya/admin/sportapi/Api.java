package com.atmiya.admin.sportapi;

import java.lang.annotation.Target;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    public static String BASE_URL="https://newsapi.org/v2/";
    @GET("top-headlines")
    Call<Sport> getInfo(@Query("sources") String Source,@Query("apiKey") String apiKey);
}
