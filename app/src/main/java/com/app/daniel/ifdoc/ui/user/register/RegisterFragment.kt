package com.app.daniel.ifdoc.ui.user.register

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.input.EmailTextWacther
import com.app.daniel.ifdoc.commons.network.NetworkChecker
import com.app.daniel.ifdoc.ui.NavActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_create_user.*
import kotlinx.android.synthetic.main.fragment_home.*


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
        navIcon.setOnClickListener(this)
        val textWatcher = EmailTextWacther()
        userEmail.addTextChangedListener(textWatcher)
        textWatcher.input(userEmail)
    }

    override fun checkConnectionStatus(isRegistered: Boolean) {
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
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    override fun showResponse(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
        nextScreen(R.id.authFragment)
    }

    override fun onClick(view: View) {
        when (view) {
            createUser -> {
                if (userEmail.text.toString().isEmpty() || userPassword.text.toString().isEmpty() || userName.text.toString().isEmpty() || userProntuario.text.toString().isEmpty()) {
                    view.let { Snackbar.make(it, getString(R.string.all_fields_are_required), Snackbar.LENGTH_LONG).show() }
                } else {
                    val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                    checkNetwork(view.context)
                }
            }
            navIcon -> {
                val intent = Intent(context,NavActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun singUp() {
        presenter.createAccount(userName.text.toString(), userEmail.text.toString(), userPassword.text.toString(), userProntuario.text.toString())
    }

}
