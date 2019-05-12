package com.app.daniel.ifdoc.ui.edict

import com.app.daniel.ifdoc.commons.application.App
import com.app.daniel.ifdoc.commons.base.BasePresenter
import com.app.daniel.ifdoc.commons.network.OkHttpFactory
import com.app.daniel.ifdoc.commons.network.RetrofitFactory
import com.app.daniel.ifdoc.data.entities.EdictEntity
import com.app.daniel.ifdoc.data.entities.responses.EdictResponseEntity
import com.app.daniel.ifdoc.data.services.edict.EdictService
import com.app.daniel.ifdoc.domain.model.Edict
import com.app.daniel.ifdoc.domain.repository.edict.EdictRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class EdictPresenter : BasePresenter<EdictMvpView>() {

    override fun attachView(edictMvpView: EdictMvpView) {
        mMvpView = edictMvpView
    }


    fun requestEdictsForUser(userId: String, token:String){

        val client = OkHttpFactory()
                .prepareClientWithToken(token)

        RetrofitFactory().setRetrofit(client)
                .create(EdictService::class.java)
                .availableEdicts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<EdictResponseEntity> {
                    override fun onSuccess(response: EdictResponseEntity) {
                        mvpView?.dismissRequestDialog()
                        val hasNoEdict = response.documents.isNullOrEmpty()
                        if (!hasNoEdict) {
                            storeEdicts(response.documents)
                        } else {
                            mMvpView?.noEdictAvailable()
                        }
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        mvpView?.dismissRequestDialog()
                        mMvpView?.onError(e.localizedMessage)
                    }

                })
    }

    private fun storeEdicts(edict: List<EdictEntity>) {
        mvpView?.showRequestDialog("Fetching data")
        EdictRepository(App.appInstance)
                .insertEdicts(edict)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Array<Long>> {
                    override fun onSuccess(databaseResponse: Array<Long>) {
                        mvpView?.dismissRequestDialog()
                        if (databaseResponse[0] > -1) {
                            retrieveFetchedEdicts()
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

    fun retrieveFetchedEdicts() {
        mvpView?.showRequestDialog("Loading")
        EdictRepository(App.appInstance).allEdicts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Edict>?> {
                    override fun onSuccess(edicts: List<Edict>) {
                        mvpView?.dismissRequestDialog()
                        mvpView?.showEdicts(edicts = edicts)
                    }

                    override fun onSubscribe(disposable: Disposable) {

                    }

                    override fun onError(throwable: Throwable) {
                        mvpView?.dismissRequestDialog()
                        mvpView?.onError(throwable.localizedMessage)
                    }
                })
    }
}