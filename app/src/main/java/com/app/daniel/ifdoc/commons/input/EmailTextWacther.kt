package com.app.daniel.ifdoc.commons.input

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import com.app.daniel.ifdoc.R


class EmailTextWacther : TextWatcher {
    private lateinit var email: EditText

    fun input(email: EditText) {
        this.email = email
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(sequence: CharSequence, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable) {
        if (!EmailValidator().validateEmail(email.text.toString())) {
            email.error = email.context.getString(R.string.error_email)
        }
    }
}