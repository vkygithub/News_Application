package com.example.task_kotlin.Interface.ResponseInterface

import com.example.task_kotlin.PojoFile.ArticlesListResponse
import com.example.task_kotlin.PojoFile.ErrorBody

interface ArticlesListResponseInterface {

    fun ArticlesListResponseProcess(articlesListResponse: ArticlesListResponse?)
    fun onFailure(errorBody: ErrorBody?, statusCode: Int)
}