package com.app.daniel.ifdoc.commons.network

import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class OkHttpFactory {

    private lateinit var interceptor: HttpLoggingInterceptor

    fun prepareClientWithToken(token: String): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val current = chain.request()
                    val newRequest = current.newBuilder().addHeader("Authorization", "Bearer ".plus(token))
                    val request = newRequest.build()
                    chain.proceed(request)
                }
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
    }


    fun prepareClient(): OkHttpClient {
        interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    fun prepareBasicAuthClient(email: String, password: String): OkHttpClient {
        val authToken = Credentials.basic(email, password)

        return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val current = chain.request()
                    val newRequest = current.newBuilder().addHeader("Authorization", authToken)
                    val request = newRequest.build()
                    chain.proceed(request)
                }
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
    }

}