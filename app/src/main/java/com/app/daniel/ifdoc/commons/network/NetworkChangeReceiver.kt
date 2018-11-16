package com.app.daniel.ifdoc.commons.network

import android.net.ConnectivityManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.util.Log


class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val manager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val wifi = manager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)

        val mobile = manager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        if (wifi.isAvailable || mobile.isAvailable) {

            Log.d("Network Available ", "Flag No 1")
        }
    }
}