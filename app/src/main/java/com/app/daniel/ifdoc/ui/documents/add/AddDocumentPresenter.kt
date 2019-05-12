package com.app.daniel.ifdoc.ui.documents.add


import android.os.Environment
import com.app.daniel.ifdoc.commons.base.BasePresenter
import com.app.daniel.ifdoc.commons.network.OkHttpFactory
import com.app.daniel.ifdoc.commons.network.RetrofitFactory
import com.app.daniel.ifdoc.data.services.document.DocumentService
import com.app.daniel.ifdoc.ui.user.register.MvpAddDocumentView
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import java.io.File


class AddDocumentPresenter : BasePresenter<MvpAddDocumentView>() {

    override fun attachView(mvpView: MvpAddDocumentView) {
        mMvpView = mvpView
    }


    fun createDocument(token: String, description: String, file: File, type: String, docType: String, id: String) {
        mvpView?.showRequestDialog("Please wait")
        val documentDescription = RequestBody.create(MediaType.parse("text/plain"), description)
        val documentType = RequestBody.create(MediaType.parse("text/plain"), docType)
        val documentId = RequestBody.create(MediaType.parse("text/plain"), id)
        val userFile = RequestBody.create(MediaType.parse(type), file)
        val filePart = MultipartBody.Part.createFormData("file", file.name, userFile)
        val client = OkHttpFactory()
                .prepareClientWithToken(token)

        RetrofitFactory().setRetrofit(client)
                .create(DocumentService::class.java)
                .createDocument(documentDescription, filePart, documentType, documentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<ResponseBody> {
                    override fun onSuccess(response: ResponseBody) {
                        mvpView?.dismissRequestDialog()
                        mMvpView?.showSuccessMessage()
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

}



