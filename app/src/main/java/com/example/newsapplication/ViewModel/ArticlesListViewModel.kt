package com.example.task_kotlin.ViewModel

import android.util.Log
import com.example.task_kotlin.Http.ApiClient
import com.example.task_kotlin.Http.ApiInterface
import com.example.task_kotlin.Interface.ResponseInterface.ArticlesListResponseInterface
import com.example.task_kotlin.PojoFile.ArticlesListResponse
import com.example.task_kotlin.PojoFile.ErrorBody
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class ArticlesListViewModel {
    var apiInterface: ApiInterface = ApiClient.apiinterface()
    private var articlesListResponseInterface: ArticlesListResponseInterface? = null

    fun ArticlesListCallEnqueue(
        articlesListResponseInterface: ArticlesListResponseInterface
    ) {
        this.articlesListResponseInterface = articlesListResponseInterface



        apiInterface.ArticlesListCall()?.enqueue(object : Callback<ArticlesListResponse?> {
            /**
             * @param call
             * @param response
             * @breif getting response from api
             */
            override fun onResponse(
                call: Call<ArticlesListResponse?>,
                response: Response<ArticlesListResponse?>
            ) {
                /**
                 * Invoked for a received HTTP response.
                 *
                 *
                 * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
                 * Call [Response.isSuccessful] to determine if the response indicates success.
                 *
                 * @param call
                 * @param response
                 */
                Log.i("responce", "response get")
                val statusCode = response.code()

                //if response is successful set the body of response to onSuccess methode in GenerateRegisterResponseModel else get the error body and set on onFailure in generateRegisterResponseModel
                if (response.isSuccessful) {
                    articlesListResponseInterface.ArticlesListResponseProcess(response.body())
                } else {
                    var serviceResponse: String? = null
                    try {
                        serviceResponse = response.errorBody()!!.string()
                        val errorBody: ErrorBody =
                            Gson().fromJson(serviceResponse, ErrorBody::class.java)
                        articlesListResponseInterface.onFailure(errorBody, statusCode)
                    } catch (e: JsonSyntaxException) {
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ArticlesListResponse?>, t: Throwable) {
                Log.d("TAG", "onTokenExpired: " + t.message)
            }

            /**
             * @param call
             * @param t
             * @breif api call failure
             */

        })
    }


}




