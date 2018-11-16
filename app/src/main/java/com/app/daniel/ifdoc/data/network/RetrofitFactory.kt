package com.app.daniel.ifdoc.data.network

import com.app.daniel.ifdoc.commons.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitFactory {

    private lateinit var interceptor: HttpLoggingInterceptor

    private lateinit var client: OkHttpClient

    private lateinit var retrofit: Retrofit


    fun prepare(token: String): Retrofit {

        client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val current = chain.request()
                    val newRequest = current.newBuilder().addHeader("Authorization", token)
                    val request = newRequest.build()
                    chain.proceed(request)
                }
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.Api.API_URL)
                .build()

        return retrofit

    }


    fun basicClient(baseUrl : String): Retrofit {
        var interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        client = OkHttpClient.Builder()
                .addInterceptor (interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

        return retrofit

    }
}