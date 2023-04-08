package com.example.midterm__2.data.remote
import com.example.midterm__2.data.dto.User
import retrofit2.http.GET

interface ApiService {
    @GET("v3.1/all")
    suspend fun getCountries() : List<User>


}