package com.app.daniel.ifdoc.data.entities

import com.google.gson.Gson

interface JsonConvertable {

    fun toJson(): String = Gson().toJson(this)
}

inline fun <reified T : JsonConvertable> String.toObject(): T = Gson().fromJson(this, T::class.java)