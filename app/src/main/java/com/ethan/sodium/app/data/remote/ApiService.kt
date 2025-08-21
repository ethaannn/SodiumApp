package com.ethan.sodium.app.data.remote

import com.ethan.sodium.app.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

}