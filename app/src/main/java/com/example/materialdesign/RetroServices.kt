package com.example.materialdesign

import retrofit2.Call
import retrofit2.http.*

interface RetroServices {
    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getUserList():Call<UserList>

    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun searchUser(@Query(value = "name") searchText:String):Call<UserList>

    @GET("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getUser(@Path(value = "user_id") user_id:String):Call<UserResponse>

    @POST("users")
    @Headers("Accept:application/json","Content-Type:application/json","Authorization: Bearer 958df00ae52ad2fd0aace295baac94430e8027882dadc368de7227ba5a27791f" )
    fun createUser(@Body params:User):Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json","Authorization: Bearer 958df00ae52ad2fd0aace295baac94430e8027882dadc368de7227ba5a27791f" )
    fun updateUser(@Path(value = "user_id") user_id:String,@Body params: User):Call<UserResponse>

    @DELETE("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json","Authorization: Bearer 958df00ae52ad2fd0aace295baac94430e8027882dadc368de7227ba5a27791f" )
    fun deleteUser(@Path(value = "user_id") user_id:String):Call<UserResponse>

}