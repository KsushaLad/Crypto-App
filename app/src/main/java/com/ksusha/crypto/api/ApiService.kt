package com.ksusha.crypto.api

import com.ksusha.crypto.response.ResponseCoinsMarkets
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("coins/markets?sparkline=true")
    suspend fun getCoinsList(@Query("vs_currency") vsCurrency: String): Response<ResponseCoinsMarkets>

}