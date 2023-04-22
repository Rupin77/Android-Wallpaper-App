package com.example.wallpaperappassignment4;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {

    String BASE_URL = "https://api.pexels.com/v1/";

    @Headers("Authorization: " + API.API)
    @GET("curated")
    Call<SearchModel> getImages(
            @Query("page") int page,
            @Query("per_page") int per_page
    );

    @Headers("Authorization: " + API.API)
    @GET("search")
    Call<SearchModel> getSearchImage(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );
}
