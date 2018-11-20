package com.app.daniel.ifdoc.ui.user.register


import android.os.Environment
import com.app.daniel.ifdoc.commons.base.BasePresenter
import com.app.daniel.ifdoc.commons.network.OkHttpFactory
import com.app.daniel.ifdoc.commons.network.RetrofitFactory
import com.app.daniel.ifdoc.data.entities.DocumentResponseEntity
import com.app.daniel.ifdoc.data.services.document.DocumentService
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


    fun createDocument(token: String, descriptor: String, file: File) {
        mvpView?.showRequestDialog("Please wait")
        val descriptor = RequestBody.create(MediaType.parse("text/plain"), descriptor)
        val userFile = RequestBody.create(MediaType.parse("image/jpg"), file)
        val filePart = MultipartBody.Part.createFormData("file", file.name, userFile)
        var client = OkHttpFactory()
                .prepareClientWithToken(token)

        RetrofitFactory().setRetrofit(client)
                .create(DocumentService::class.java)
                .createDocument(descriptor, filePart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<DocumentResponseEntity> {
                    override fun onSuccess(response: DocumentResponseEntity) {
                        mvpView?.dismissRequestDialog()
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
        var folder = File(Environment.getExternalStorageDirectory(), "ifdocs/")
        folder.mkdirs()
        return folder
    }
}


