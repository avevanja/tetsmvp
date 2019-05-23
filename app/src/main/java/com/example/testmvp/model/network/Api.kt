package com.example.testmvp.model.network

import com.example.testmvp.entity.Post
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("posts")
    fun getPosts(): Call<ArrayList<Post>>
}