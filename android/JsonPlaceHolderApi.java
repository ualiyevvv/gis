package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @GET("geo")
    Call<List<Entity>> getActivity();

    @POST("geo")
    Call<Entity> createActivity(@Body Entity entity);

    @POST("geo")
    Call<String> create(@Body String str);





}
