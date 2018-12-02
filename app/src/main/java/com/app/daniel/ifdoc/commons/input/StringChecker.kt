package com.app.daniel.ifdoc.commons.input

class StringChecker {

    fun nullToDash(value: String?): String {
        return if (value == null || value.trim { it <= ' ' }.isEmpty()) "-" else value
    }

    fun emptyOverview(overview: String?): String? {
        return if (overview == null || overview.trim { it <= ' ' }.isNotEmpty()) overview else "No information available."
    }
}