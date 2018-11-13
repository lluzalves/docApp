package com.app.daniel.ifdoc.ui

import android.os.Bundle
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseActivity
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.data.network.RetrofitFactory
import com.app.daniel.ifdoc.domain.repository.documents.toDocument
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.w3c.dom.Document

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RetrofitFactory().create()
                .myDocuments("ac303bece169a8f6007765371bbc880d43d883d8b36a2e6868c1317a7a9f94530c02f31d53ee9418b995cc3f23a41fae5a59e332b7442d3888ab8002146480a7")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<DocumentEntity>{
                    override fun onSuccess(t: DocumentEntity) {
                        t.toDocument()
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        e.localizedMessage;
                    }

                })
    }

}
