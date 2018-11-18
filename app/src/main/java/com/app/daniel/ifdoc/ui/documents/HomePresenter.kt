package com.app.daniel.ifdoc.ui.documents

import com.app.daniel.ifdoc.commons.base.BasePresenter

class HomePresenter : BasePresenter<MvpHomeView>() {

    override fun attachView(mvpView: MvpHomeView) {
        mMvpView = mvpView
    }

}