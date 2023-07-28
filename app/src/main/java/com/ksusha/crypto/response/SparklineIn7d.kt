package com.ksusha.crypto.response

import com.google.gson.annotations.SerializedName

data class SparklineIn7d(
    @SerializedName("price")
    val price: List<Double?>?
)
