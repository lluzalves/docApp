package com.app.daniel.ifdoc.commons.base

import android.content.Context

interface Presenter<V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()

    fun checkInternetConnection(context : Context) {

    }
}
