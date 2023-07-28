package com.ksusha.crypto.response

import com.google.gson.annotations.SerializedName

data class MarketData(
    @SerializedName("current_price")
    val currentPrice: CurrentPrice?,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double?,
    @SerializedName("sparkline_7d")
    val sparkline7d: Sparkline7d?
)
