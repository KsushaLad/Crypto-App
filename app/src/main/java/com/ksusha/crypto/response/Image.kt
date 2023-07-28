package com.ksusha.crypto.response

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("large")
    val large: String?
)
