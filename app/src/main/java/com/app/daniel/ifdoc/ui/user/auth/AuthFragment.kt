package com.app.daniel.ifdoc.ui.user.auth


import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.afollestad.materialdialogs.MaterialDialog
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.network.NetworkChecker
import com.app.daniel.ifdoc.commons.network.Token.getToken
import com.app.daniel.ifdoc.commons.security.Base64Helper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*


class AuthFragment : BaseFragment(), MvpAuthView, View.OnClickListener {

    private lateinit var dialog: ProgressDialog
    private var presenter = AuthPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        if (isTokenStored()) {
            Navigation.findNavController(view)
                    .navigate(R.id.documentsFragment, null,
                            NavOptions.Builder().setPopUpTo(R.id.authFragment, true)
                                    .build())
        } else {
            context?.let { checkNetwork(it) }
        }
        checkConnection.setOnClickListener(this)
        noAccount.setOnClickListener(this)
        startLogin.setOnClickListener(this)
        terms.setOnClickListener(this)
    }

    private fun isTokenStored(): Boolean {
        return getToken() != "default"
    }

    override fun showLogin(isInternetAvailable: Boolean) {
        login.isVisible = isInternetAvailable
        noConnection.isVisible = !isInternetAvailable
    }

    override fun checkConnectionStatus(status: Boolean) {
        showLogin(status)
    }

    private fun checkNetwork(context: Context) {
        if (NetworkChecker(context).isNetworkActive()) {
            presenter.checkInternetConnection(context)
        } else {
            showLogin(false)
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

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val logout = menu.findItem(R.id.action_logout)
        logout.isVisible = false
    }


    override fun showResponse(message: String) {
        view?.let { it ->
            MaterialDialog(it.context).icon(R.drawable.ic_warning_black)
                    .title(R.string.warning)
                    .message(null, getString(R.string.failed_to_autenticated), false, 1F)
                    .show {
                        positiveButton(R.string.ok, click = MaterialDialog::dismiss)
                    }.positiveButton {
                        it.dismiss()
                    }
        }
    }

    override fun storeToken(token: String) {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(Base64Helper().encrypt("token"), Base64Helper().encrypt(token))
        editor.apply()
    }

    override fun onClick(view: View) {
        when (view) {
            checkConnection -> checkNetwork(view.context)
            noAccount -> nextScreen(R.id.registerFragment)
            terms -> nextScreen(R.id.termsAndConditionsFragment)
            startLogin -> {
                val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                presenter.login(email.text.toString(), password.text.toString())
            }
        }
    }

    override fun showRecoverPassword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
