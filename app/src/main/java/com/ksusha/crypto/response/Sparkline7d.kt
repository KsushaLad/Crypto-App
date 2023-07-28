package com.ksusha.crypto.response

import com.google.gson.annotations.SerializedName

data class Sparkline7d(
    @SerializedName("price")
    val price: List<Double?>?,
)
