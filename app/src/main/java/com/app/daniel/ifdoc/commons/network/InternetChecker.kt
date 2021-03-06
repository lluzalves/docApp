package com.app.daniel.ifdoc.commons.network

import android.os.AsyncTask
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class InternetChecker(private val status: NetworkListener) : AsyncTask<Void, Void, Boolean>() {

    val urlConnection = URL("http://www.google.com").openConnection() as HttpURLConnection

    override fun onPreExecute() {
        urlConnection.setRequestProperty("User-Agent", "Test")
        urlConnection.setRequestProperty("Connection", "close")
        urlConnection.connectTimeout = 5000
    }

    override fun doInBackground(vararg voids: Void): Boolean? {
        try {
            urlConnection.connect()

            return when (urlConnection.responseCode) {
                200 -> true
                else -> false
            }

        } catch (malFormedException: MalformedURLException) {
            malFormedException.printStackTrace()
            return false

        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return false
        }
    }

    override fun onPostExecute(result: Boolean) {
        status.isOnline(result)
    }

}