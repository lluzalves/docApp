package com.app.daniel.ifdoc.ui.user.auth


import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.view.FragmentReplacer
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.network.NetworkChecker
import com.app.daniel.ifdoc.commons.network.Token.getToken
import com.app.daniel.ifdoc.commons.security.Base64Helper
import com.app.daniel.ifdoc.ui.user.register.RegisterFragment
import com.app.daniel.ifdoc.ui.documents.DocumentsFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
import org.w3c.dom.DocumentFragment

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
        if (isTokenStored()) {
            showHome()
        } else {
            context?.let { checkNetwork(it) }
        }
        checkConnection.setOnClickListener(this)
        noAccount.setOnClickListener(this)
        startLogin.setOnClickListener(this)
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

    override fun showResponse(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
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
            noAccount -> showSignUp()
            startLogin -> {
                val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                presenter.login(email.text.toString(), password.text.toString())
            }
        }
    }

    override fun showSignUp() {
        var fragment = RegisterFragment()
        val bundle = Bundle()
        bundle.putInt("view_to_circular_animation_x", noAccount.x.toInt())
        bundle.putInt("view_to_circular_animation_y", noAccount.y.toInt())
        fragmentManager?.let { manager -> FragmentReplacer().addFragment(fragment, manager, bundle, R.id.container) }
    }

    override fun showHome() {
        var fragment = DocumentsFragment()
        val bundle = Bundle()
        bundle.putInt("view_to_circular_animation_x", noAccount.x.toInt())
        bundle.putInt("view_to_circular_animation_y", noAccount.y.toInt())
        fragmentManager?.let { manager -> FragmentReplacer().replaceFragment(fragment, manager, bundle, R.id.container) }
    }

    override fun showRecoverPassword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
