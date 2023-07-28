package com.ksusha.crypto.response

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("homepage")
    val homepage: List<String?>?,
)
