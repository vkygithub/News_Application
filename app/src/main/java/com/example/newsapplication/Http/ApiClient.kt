package com.example.task_kotlin.Http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var retrofit: Retrofit? = null

    fun apiinterface(): ApiInterface {

        if (retrofit == null) {
            val defaultHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(2, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .build()
            retrofit = Retrofit.Builder()
                .baseUrl(ApiClass!!.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(defaultHttpClient)
                .build()
        }
        return retrofit!!.create(ApiInterface::class.java)
    }


}