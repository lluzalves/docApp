package com.app.daniel.ifdoc.commons.base

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

    class MvpViewNotAttachedException : RuntimeException("Should call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")
}