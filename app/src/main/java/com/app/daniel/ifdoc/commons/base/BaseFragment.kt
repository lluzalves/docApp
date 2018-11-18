package com.app.daniel.ifdoc.commons.base

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import com.app.daniel.ifdoc.commons.security.Base64Helper


abstract class BaseFragment : Fragment(), MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    protected fun getToken(): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val encryptedToken = preferences.getString(Base64Helper().encrypt("token"), Base64Helper().encrypt("default"))
        return Base64Helper().decrypt(encryptedToken)
    }

    override fun onError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun previousScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun nextScreen(flowIntention: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}