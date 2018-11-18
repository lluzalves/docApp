package com.app.daniel.ifdoc.ui.auth


import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.FragmentReplacer
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.network.NetworkChecker
import com.app.daniel.ifdoc.ui.auth.register.RegisterFragment
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
        context?.let {
            checkNetwork(it)
        }
        checkConnection.setOnClickListener(this)
        noAccount.setOnClickListener(this)
        startLogin.setOnClickListener(this)
    }

    override fun showLogin(isInternetAvailable: Boolean) {
        login.isVisible = isInternetAvailable
        noConnection.isVisible = !isInternetAvailable
    }

    override fun connectionStatus(status: Boolean) {
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

    override fun onError(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
    }

    override fun showResponse(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
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

    override fun showDashboard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRecoverPassword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
