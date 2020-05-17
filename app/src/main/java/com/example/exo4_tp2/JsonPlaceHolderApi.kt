package com.example.exo4_tp2

import retrofit2.Call
import retrofit2.http.GET


interface JsonPlaceHolderApi {
    @get:GET("posts")
    val posts: Call<List<Post>>
}
