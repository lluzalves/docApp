package com.app.daniel.ifdoc.commons.input

import java.util.regex.Matcher
import java.util.regex.Pattern

class EmailValidator {
    var emailRegex = "[_a-z0-9-]+(\\.[_a-z0-9-]+)*@(?i)ifsp.edu.br"
    private lateinit var pattern: Pattern
    private lateinit var matcher: Matcher


    fun validateEmail(email: String): Boolean {
        pattern = Pattern.compile(emailRegex)
        matcher = pattern.matcher(email)
        return matcher.matches()
    }

}
