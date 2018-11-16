package com.app.daniel.ifdoc.commons.network

import android.content.Context
import android.net.ConnectivityManager


class NetworkChecker constructor(private val context: Context) {
    var status: Boolean? = null

    fun isNetworkActive(): Boolean {
        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

   fun checkInternet(networkListener: NetworkListener) {
       InternetChecker(networkListener).execute()

    }
}

