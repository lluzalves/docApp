package com.app.daniel.ifdoc.commons.security

import android.util.Base64

class Base64Helper {

    fun encrypt(input: String): String {
        return Base64.encodeToString(input.toByteArray(), Base64.DEFAULT)
    }

    fun decrypt(input: String): String {
        return String(Base64.decode(input, Base64.DEFAULT))
    }
}