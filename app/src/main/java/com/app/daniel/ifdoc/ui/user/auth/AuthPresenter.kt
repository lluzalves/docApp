package com.app.daniel.ifdoc.ui.user.auth


import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.application.App
import com.app.daniel.ifdoc.commons.base.BasePresenter
import com.app.daniel.ifdoc.commons.network.OkHttpFactory
import com.app.daniel.ifdoc.commons.network.RetrofitFactory
import com.app.daniel.ifdoc.data.entities.UserEntity
import com.app.daniel.ifdoc.data.entities.responses.ResponseEntity
import com.app.daniel.ifdoc.data.entities.responses.UserResponseEntity
import com.app.daniel.ifdoc.data.repository.user.UserRepository
import com.app.daniel.ifdoc.data.services.user.UserService
import com.app.daniel.ifdoc.domain.model.User
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
                        getUserDetails(response.token, email)
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        mMvpView?.dismissRequestDialog()
                        mMvpView?.showResponse("Error : ".plus(e.localizedMessage))
                    }

                })
    }

    private fun getUserDetails(token: String, email: String) {
        mvpView?.showRequestDialog("Please wait")

        val client = OkHttpFactory()
                .prepareClientWithToken(token)

        RetrofitFactory().setRetrofit(client)
                .create(UserService::class.java)
                .getCurrentUserInfo(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<UserResponseEntity> {
                    override fun onSuccess(response: UserResponseEntity) {
                        mvpView?.dismissRequestDialog()
                        storeUserDetails(user = response.user)
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        mvpView?.dismissRequestDialog()
                        mMvpView?.onError(e.localizedMessage)
                    }

                })
    }

    private fun storeUserDetails(user: UserEntity) {
        mvpView?.showRequestDialog("Fetching data")
        UserRepository(App.appInstance)
                .insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Long> {
                    override fun onSuccess(databaseResponse: Long) {
                        mvpView?.dismissRequestDialog()
                        if (databaseResponse > -1) {
                            mvpView?.storeUserId(user.id)
                            mvpView?.nextScreen(R.id.action_authFragment_to_edictFragment)
                        }
                    }

                    override fun onSubscribe(disposable: Disposable) {

                    }

                    override fun onError(throwable: Throwable) {
                        mvpView?.dismissRequestDialog()
                        mMvpView?.onError(throwable.localizedMessage)
                    }

                })
    }

}
