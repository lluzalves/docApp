package com.app.daniel.ifdoc.commons.input

import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

class EmailValidator {
    private lateinit var pattern: Pattern
    private lateinit var matcher: Matcher


    fun validateEmail(email: String): Boolean {
        pattern = Patterns.EMAIL_ADDRESS
        matcher = pattern.matcher(email)
        return matcher.matches()
    }

}
