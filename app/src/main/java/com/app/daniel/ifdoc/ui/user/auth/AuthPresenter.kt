package com.app.daniel.ifdoc.ui.user.auth


import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BasePresenter
import com.app.daniel.ifdoc.commons.network.OkHttpFactory
import com.app.daniel.ifdoc.commons.network.RetrofitFactory
import com.app.daniel.ifdoc.data.entities.responses.ResponseEntity
import com.app.daniel.ifdoc.data.services.user.UserService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class AuthPresenter : BasePresenter<MvpAuthView>() {

    override fun attachView(mvpView: MvpAuthView) {
        mMvpView = mvpView
    }

    fun login(email: String, password: String) {
        mMvpView?.showRequestDialog("Please wait")

        var client = OkHttpFactory()
                .prepareBasicAuthClient(email, password)

        RetrofitFactory().setRetrofit(client)
                .create(UserService::class.java)
                .login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<ResponseEntity> {
                    override fun onSuccess(response: ResponseEntity) {
                        mMvpView?.dismissRequestDialog()
                        mvpView?.storeToken(response.token)
                        mvpView?.nextScreen(R.id.authFragment)
                    }
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        mMvpView?.dismissRequestDialog()
                        mMvpView?.showResponse("Error : ".plus(e.localizedMessage))
                    }

                })
    }

}
