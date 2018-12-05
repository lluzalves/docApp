package com.app.daniel.ifdoc.ui.documents

import com.app.daniel.ifdoc.commons.application.App
import com.app.daniel.ifdoc.commons.base.BasePresenter
import com.app.daniel.ifdoc.commons.network.OkHttpFactory
import com.app.daniel.ifdoc.commons.network.RetrofitFactory
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.data.entities.responses.DocumentResponseEntity
import com.app.daniel.ifdoc.data.services.document.DocumentService
import com.app.daniel.ifdoc.domain.model.Document
import com.app.daniel.ifdoc.domain.repository.documents.DocumentRepository
import com.app.daniel.ifdoc.domain.repository.documents.toDocument
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomePresenter : BasePresenter<MvpHomeView>() {

    override fun attachView(mvpView: MvpHomeView) {
        mMvpView = mvpView
    }

    fun requestUserDocuments(token: String) {
        mvpView?.showRequestDialog("Please wait")

        val client = OkHttpFactory()
                .prepareClientWithToken(token)

        RetrofitFactory().setRetrofit(client)
                .create(DocumentService::class.java)
                .myDocuments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<DocumentResponseEntity> {
                    override fun onSuccess(response: DocumentResponseEntity) {
                        mvpView?.dismissRequestDialog()
                        val hasNoDocuments = response.documents.isNullOrEmpty()
                        if (!hasNoDocuments) {
                            storeDocuments(response.documents)
                        } else {
                            mMvpView?.emptyDocuments()
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

    private fun storeDocuments(documents: List<DocumentEntity>) {
        mvpView?.showRequestDialog("Fetching data")
        DocumentRepository(App.appInstance)
                .insertDocuments(documents)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Array<Long>> {
                    override fun onSuccess(databaseResponse: Array<Long>) {
                        mvpView?.dismissRequestDialog()
                        if (databaseResponse[0] > -1) {
                            mvpView?.retrieveFetchedDocuments()
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

    fun retrieveFetchedDocuments() {
        mvpView?.showRequestDialog("Loading")
        DocumentRepository(App.appInstance).allDocuments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Document>?> {
                    override fun onSuccess(documents: List<Document>) {
                        mvpView?.dismissRequestDialog()
                        mvpView?.showDocuments(documents)
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