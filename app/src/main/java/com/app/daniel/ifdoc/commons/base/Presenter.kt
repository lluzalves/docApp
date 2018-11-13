package com.app.daniel.ifdoc.commons.base

interface Presenter<V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()
}
