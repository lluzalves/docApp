package com.app.daniel.ifdoc.commons.network

import android.preference.PreferenceManager
import com.app.daniel.ifdoc.commons.application.App
import com.app.daniel.ifdoc.commons.security.Base64Helper

object Token {
    fun getToken(): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(App.appInstance)
        val encryptedToken = preferences.getString(Base64Helper().encrypt("token"), Base64Helper().encrypt("default"))
        return Base64Helper().decrypt(encryptedToken)
    }
}