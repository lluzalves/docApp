package com.app.daniel.ifdoc.ui.user.register


import com.app.daniel.ifdoc.commons.base.BasePresenter
import com.app.daniel.ifdoc.commons.network.OkHttpFactory
import com.app.daniel.ifdoc.commons.network.RetrofitFactory
import com.app.daniel.ifdoc.data.entities.responses.UserResponseEntity
import com.app.daniel.ifdoc.data.services.user.UserService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class RegisterPresenter : BasePresenter<MvpRegisterView>() {

    override fun attachView(mvpView: MvpRegisterView) {
        mMvpView = mvpView
    }


    fun createAccount(name: String, email: String, password: String, prontuario: String) {
        val client = OkHttpFactory()
                .prepareClient()
        mMvpView?.showRequestDialog("Please wait")
        RetrofitFactory().setRetrofit(client)
                .create(UserService::class.java)
                .createUser(name, email, prontuario, "aluno", password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<UserResponseEntity> {
                    override fun onSuccess(response: UserResponseEntity) {
                        mMvpView?.dismissRequestDialog()
                        mMvpView?.showResponse(response.message)
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        mMvpView?.dismissRequestDialog()
                        mMvpView?.onError("Error : ".plus(e.localizedMessage))
                    }

                })
    }


}
