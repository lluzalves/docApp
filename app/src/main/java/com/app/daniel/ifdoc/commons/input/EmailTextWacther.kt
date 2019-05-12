package com.app.daniel.ifdoc.commons.input

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import com.app.daniel.ifdoc.R
import kotlinx.android.synthetic.main.fragment_login.view.*


class FormTextWacther : TextWatcher {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var name: EditText
    private lateinit var userProntuario: EditText
    private lateinit var confirmAction: ImageView

    fun input(email: EditText, password: EditText, name: EditText, confirmAction: ImageView, userProntuario: EditText) {
        this.email = email
        this.password = password
        this.name = name
        this.confirmAction = confirmAction
        this.userProntuario = userProntuario
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(sequence: CharSequence, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable) {
        if (!EmailValidator().validateEmail(email.text.toString())) {
            email.error = email.context.getString(R.string.invalid_email)
        } else {
            email.error = null
        }
    }
}