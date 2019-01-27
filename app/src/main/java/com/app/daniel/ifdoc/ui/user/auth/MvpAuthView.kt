package com.app.daniel.ifdoc.ui.user.auth

import com.app.daniel.ifdoc.commons.base.MvpView

interface MvpAuthView : MvpView{

    fun showLogin(isInternetAvailable : Boolean)
    fun storeToken(token: String)
    fun showRecoverPassword()
}