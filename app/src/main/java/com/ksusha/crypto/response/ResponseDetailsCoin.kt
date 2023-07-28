package com.ksusha.crypto.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailsCoin(
    @SerializedName("categories")
    val categories: List<String?>?,
    @SerializedName("description")
    val description: Description?,
    @SerializedName("genesis_date")
    val genesisDate: String?,
    @SerializedName("image")
    val image: Image?,
    @SerializedName("links")
    val links: Links?,
    @SerializedName("market_data")
    val marketData: MarketData?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?
)