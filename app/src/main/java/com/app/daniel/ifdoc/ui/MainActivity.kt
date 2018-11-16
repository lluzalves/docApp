package com.app.daniel.ifdoc.ui

import android.os.Bundle
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.FragmentReplacer
import com.app.daniel.ifdoc.commons.base.BaseActivity
import com.app.daniel.ifdoc.data.entities.ResponseEntity
import com.app.daniel.ifdoc.data.network.RetrofitFactory
import com.app.daniel.ifdoc.data.services.DocumentService
import com.app.daniel.ifdoc.ui.auth.LoginFragment
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            var fragment = LoginFragment()
            FragmentReplacer().loadInitialActivityFragment(fragment, this, R.id.container)
        }
    }

    fun pullData() {
        RetrofitFactory()
                .prepare("Bearer 7441b173c6a4a907")
                .create(DocumentService::class.java)
                .myDocuments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<ResponseEntity> {
                    override fun onSuccess(response: ResponseEntity) {
                        response.documents
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        e.localizedMessage
                    }

                })
    }

}
