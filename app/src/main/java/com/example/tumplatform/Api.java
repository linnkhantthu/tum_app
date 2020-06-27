package com.example.tumplatform;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/home/json")
    Call<List<Posts>> getPosts();

    @GET("/comments/json")
    Call<List<Comments>> getComments();

}
