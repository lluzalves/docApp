package com.app.daniel.ifdoc.ui.user.register

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.input.FormTextWacther
import com.app.daniel.ifdoc.commons.network.NetworkChecker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_create_user.*


class RegisterFragment : BaseFragment(), MvpRegisterView, View.OnClickListener {

    private lateinit var dialog: ProgressDialog
    private var presenter = RegisterPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createUser.setOnClickListener(this)
        var textWatcher = FormTextWacther()
        userEmail.addTextChangedListener(textWatcher)
        userPassword.addTextChangedListener(textWatcher)
        userName.addTextChangedListener(textWatcher)
        textWatcher.input(userEmail, userPassword, userName, createUser)
    }

    override fun connectionStatus(isRegistered: Boolean) {
        if (isRegistered) {
            singUp()
        } else {
            view?.let { Snackbar.make(it, getString(R.string.register_failure), Snackbar.LENGTH_LONG).show() }
        }
    }

    private fun checkNetwork(context: Context) {
        if (NetworkChecker(context).isNetworkActive()) {
            presenter.checkInternetConnection(context)
        } else {
            view?.let { Snackbar.make(it, getString(R.string.no_connection), Snackbar.LENGTH_LONG).show() }
        }
    }

    override fun showRequestDialog(message: String) {
        dialog = ProgressDialog.show(context, getString(R.string.message_wait), message)
    }

    override fun dismissRequestDialog() {
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
    }

    override fun showResponse(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
        previousScreen()
    }

    override fun onClick(view: View) {
        when (view) {
            createUser -> {
                val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                checkNetwork(view.context)
            }
        }
    }

    override fun singUp() {
        presenter.createAccount(userName.text.toString(), userEmail.text.toString(), userPassword.text.toString())
    }

    override fun previousScreen() {
        super.previousScreen()
        fragmentManager?.popBackStack()
    }

}
