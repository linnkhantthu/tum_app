package com.example.tumplatform;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("/home/json")
    Call<List<Posts>> getPosts();

    @GET("/comments/json")
    Call<List<Comments>> getComments();

    @GET("/users/json/{user_id}")
    Call<List<author>> getProfiles(@Path("user_id") int userId);

    @GET("/users/posts/json/{user_id}")
    Call<List<Posts>> getUserPosts(@Path("user_id") int userId);

    @GET("/comments/json/{user_id}")
    Call<List<Comments>> getUserComments(@Path("user_id") int userId);

}
