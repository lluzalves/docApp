package com.app.daniel.ifdoc.ui.documents.details

import com.app.daniel.ifdoc.commons.application.App
import com.app.daniel.ifdoc.commons.base.BasePresenter
import com.app.daniel.ifdoc.commons.network.OkHttpFactory
import com.app.daniel.ifdoc.commons.network.RetrofitFactory
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.data.entities.responses.DocumentResponseEntity
import com.app.daniel.ifdoc.data.services.document.DocumentService
import com.app.daniel.ifdoc.domain.repository.documents.DocumentRepository
import com.app.daniel.ifdoc.domain.repository.documents.toDocument
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DocumentDetailPresenter : BasePresenter<DocumentDetailMvpView>() {

    override fun attachView(documentDetailMvpView: DocumentDetailMvpView) {
        mMvpView = documentDetailMvpView
    }

    fun requestDocument(token: String, documentId: Int) {
        mvpView?.showRequestDialog("Please wait")

        val client = OkHttpFactory()
                .prepareClientWithToken(token)

        RetrofitFactory().setRetrofit(client)
                .create(DocumentService::class.java)
                .getDocument(documentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<DocumentResponseEntity> {
                    override fun onSuccess(response: DocumentResponseEntity) {
                        mvpView?.dismissRequestDialog()
                        val hasNoDocuments = response.documents.isNullOrEmpty()
                        if (!hasNoDocuments) {
                            updateDocument(response.documents[0])
                        } else {
                            mMvpView?.onError("Unable to retrieve details for selected document")
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

    private fun updateDocument(documentEntity: DocumentEntity) {
        mvpView?.showRequestDialog("Fetching data")
        DocumentRepository(App.appInstance)
                .updateDocument(documentEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Int> {
                    override fun onSuccess(databaseResponse: Int) {
                        mvpView?.dismissRequestDialog()
                        if (databaseResponse > -1) {
                            mMvpView?.showDocument(documentEntity.toDocument())
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