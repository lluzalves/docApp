package com.app.daniel.ifdoc.data.entities

import com.google.gson.annotations.SerializedName
import java.util.*

data class UpdateAt(
        val date: Date,
        @SerializedName("timezone_type")
        var timezoneType: Int,
        var timezone: String
)
