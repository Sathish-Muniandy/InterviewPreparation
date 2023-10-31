package com.example.interviewpreparation.ApiInterface

import com.example.interviewpreparation.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ApiCall {

    @GET("products")
    suspend fun getProducts() : Response<List<Product>>

}