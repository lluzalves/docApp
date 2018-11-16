package com.app.daniel.ifdoc.commons.base

import android.content.Context
import com.app.daniel.ifdoc.commons.network.NetworkChecker
import com.app.daniel.ifdoc.commons.network.NetworkListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class BasePresenter<T : MvpView> : Presenter<T> {

    var mMvpView: T? = null

    private val isViewAttached: Boolean
        get() = mMvpView != null

    val mvpView: T?
        get() {
            checkViewAttached()
            return mMvpView
        }

    override fun attachView(mvpView: T) {
        mMvpView = mvpView
    }

    override fun detachView() {
        mMvpView = null
    }

    private fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    override fun checkInternetConnection(context : Context) {
        mMvpView?.showRequestDialog("Checking internet status")
        GlobalScope.launch {
            var networkListener = object : NetworkListener {
                override fun isOnline(result: Boolean) {
                    if (result) {
                        mMvpView?.connectionStatus(result)
                        mMvpView?.dismissRequestDialog()
                    }
                }
            }
            NetworkChecker(context).checkInternet(networkListener)
        }
    }

    class MvpViewNotAttachedException : RuntimeException("Should call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")
}