package com.app.daniel.ifdoc.commons.base

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import com.app.daniel.ifdoc.commons.security.Base64Helper
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment : Fragment(), MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onError(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
    }

    override fun previousScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun nextScreen(flowIntention: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}