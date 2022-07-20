package com.example.apidatapractise.network


import com.example.apidatapractise.Model.User
import com.example.apidatapractise.Model.UserList

import retrofit2.Call
import retrofit2.http.*

interface RetroService {

    //https://gorest.co.in/public-api/users
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    suspend fun getUsersList(): UserList

    //https://gorest.co.in/public-api/users?name=a
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
     fun searchUsers(@Query("name") searchText: String): Call<UserList>


}