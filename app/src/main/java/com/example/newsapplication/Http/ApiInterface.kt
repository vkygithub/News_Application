package com.example.task_kotlin.Http

import com.example.task_kotlin.PojoFile.ArticlesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET(ApiClass.ARTICLESLIST)
    fun ArticlesListCall(
        @Query("format")format: String = "json",
        @Query("limit")limit: Int = 50,
        @Query("offset")offset: Int = 0
    ): Call<ArticlesListResponse?>?


}