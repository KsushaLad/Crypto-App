package com.ksusha.crypto.api

import com.ksusha.crypto.response.ResponseCoinsMarkets
import com.ksusha.crypto.response.ResponseDetailsCoin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("coins/markets?sparkline=true")
    suspend fun getCoinsList(@Query("vs_currency") vsCurrency: String): Response<ResponseCoinsMarkets>

    @GET("coins/{id}?sparkline=true")
    suspend fun getDetailsCoin(@Path("id") id: String) : Response<ResponseDetailsCoin>

}