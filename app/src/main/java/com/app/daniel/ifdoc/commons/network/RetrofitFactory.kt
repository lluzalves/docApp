package com.app.daniel.ifdoc.commons.network

import com.app.daniel.ifdoc.commons.api.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitFactory {

    private lateinit var retrofit: Retrofit

    fun setRetrofit(client: OkHttpClient): Retrofit {
        retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.Api.API_URL)
                .build()

        return retrofit
    }
}