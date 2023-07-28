package com.ksusha.crypto.response

import com.google.gson.annotations.SerializedName

class ResponseCoinsMarkets : ArrayList<ResponseCoinsMarkets.ResponseCoinsMarketsItem>(){
    data class ResponseCoinsMarketsItem(
        @SerializedName("current_price")
        val currentPrice: Double?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("image")
        val image: String?,
        @SerializedName("sparkline_in_7d")
        val sparklineIn7d: SparklineIn7d?,
        @SerializedName("symbol")
        val symbol: String?
    )
}