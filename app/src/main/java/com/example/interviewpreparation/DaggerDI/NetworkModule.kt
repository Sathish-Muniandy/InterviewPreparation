package com.example.interviewpreparation.DaggerDI

import com.example.interviewpreparation.ApiInterface.ApiCall
import com.example.interviewpreparation.Utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofitInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getApiCallInstance(retrofit: Retrofit) : ApiCall {
        return retrofit.create(ApiCall::class.java)
    }


}