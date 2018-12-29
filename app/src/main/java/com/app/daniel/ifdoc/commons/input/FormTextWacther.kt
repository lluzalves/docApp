package com.app.daniel.ifdoc.commons.input

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.isVisible
import com.app.daniel.ifdoc.R


class FormTextWacther : TextWatcher {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var name: EditText
    private lateinit var confirmAction: ImageView

    fun input(email: EditText, password: EditText, name: EditText, confirmAction: ImageView) {
        this.email = email
        this.password = password
        this.name = name
        this.confirmAction = confirmAction
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(sequence: CharSequence, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable) {
        if (!EmailValidator().validateEmail(email.text.toString())) {
            email.error = email.context.getString(R.string.error_email)
            confirmAction.isVisible = false
        } else {
            confirmAction.isVisible = password.text.isNotBlank() && name.text.isNotBlank()
        }
    }
}