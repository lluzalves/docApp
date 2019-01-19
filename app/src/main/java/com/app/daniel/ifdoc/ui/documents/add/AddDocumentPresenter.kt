package com.app.daniel.ifdoc.ui.documents.add


import android.os.Environment
import com.app.daniel.ifdoc.commons.application.App
import com.app.daniel.ifdoc.commons.base.BasePresenter
import com.app.daniel.ifdoc.commons.network.OkHttpFactory
import com.app.daniel.ifdoc.commons.network.RetrofitFactory
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.data.entities.responses.DocumentResponseEntity
import com.app.daniel.ifdoc.data.services.document.DocumentService
import com.app.daniel.ifdoc.domain.repository.documents.DocumentRepository
import com.app.daniel.ifdoc.ui.user.register.MvpAddDocumentView
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class AddDocumentPresenter : BasePresenter<MvpAddDocumentView>() {

    override fun attachView(mvpView: MvpAddDocumentView) {
        mMvpView = mvpView
    }


    fun createDocument(token: String, description: String, file: File, type: String, docType: String) {
        mvpView?.showRequestDialog("Please wait")
        val documentDescription = RequestBody.create(MediaType.parse("text/plain"), description)
        val documentType = RequestBody.create(MediaType.parse("text/plain"), docType)
        val userFile = RequestBody.create(MediaType.parse(type), file)
        val filePart = MultipartBody.Part.createFormData("file", file.name, userFile)
        val client = OkHttpFactory()
                .prepareClientWithToken(token)

        RetrofitFactory().setRetrofit(client)
                .create(DocumentService::class.java)
                .createDocument(documentDescription, filePart, documentType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<DocumentResponseEntity> {
                    override fun onSuccess(response: DocumentResponseEntity) {
                        mvpView?.dismissRequestDialog()
                        mMvpView?.showSuccessMessage(response)
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        mvpView?.dismissRequestDialog()
                        mMvpView?.onError(e.localizedMessage)
                    }

                })
    }

    fun setFolder(): File {
        val folder = File(Environment.getExternalStorageDirectory(), "ifdocs/")
        folder.mkdirs()
        return folder
    }

    fun updateLocalDatabase(documents: List<DocumentEntity>) {
        DocumentRepository(App.appInstance)
                .insertDocuments(documents)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Array<Long>> {
                    override fun onSuccess(databaseResponse: Array<Long>) {
                        mvpView?.dismissRequestDialog()
                        mvpView?.previousScreen()
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



