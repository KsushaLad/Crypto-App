package com.ksusha.crypto.response

import com.google.gson.annotations.SerializedName

data class CurrentPrice(
    @SerializedName("eur")
    val eur: Double?,
)
