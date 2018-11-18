package com.app.daniel.ifdoc.ui.auth

import com.app.daniel.ifdoc.commons.base.MvpView

interface MvpAuthView : MvpView{

    fun showLogin(isInternetAvailable : Boolean)
    fun showSignUp()
    fun showHome()
    fun storeToken(token: String)
    fun showRecoverPassword()
}