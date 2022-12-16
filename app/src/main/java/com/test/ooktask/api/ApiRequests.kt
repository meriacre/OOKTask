package com.test.ooktask.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiRequests {

    @Headers("Token: ookgroup")
    @GET("postcards/page/home")
    suspend fun geImages(@Query("limit") limit: Int, @Query("page") page: Int): Response<Any>
}