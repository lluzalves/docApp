package com.app.daniel.ifdoc.data.entities

import com.google.gson.annotations.SerializedName

data class UpdateAt(
        val date: String,
        @SerializedName("timezone_type")
        var timezoneType: Int,
        var timezone: String
)
