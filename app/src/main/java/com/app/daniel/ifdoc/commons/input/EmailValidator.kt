package com.app.daniel.ifdoc.commons.input

import java.util.regex.Matcher
import java.util.regex.Pattern

class EmailValidator {
    var emailRegex = "[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" +
            "" +
            ""
    private lateinit var pattern: Pattern
    private lateinit var matcher: Matcher


    fun validateEmail(email: String): Boolean {
        pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE)
        matcher = pattern.matcher(email)
        return matcher.matches()
    }

}
