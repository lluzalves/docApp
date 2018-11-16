package com.app.daniel.ifdoc.ui.auth


import com.app.daniel.ifdoc.commons.base.BasePresenter


class LoginPresenter : BasePresenter<MvpAuthView>() {

    override fun attachView(mvpView: MvpAuthView) {
        mMvpView = mvpView
    }


}
