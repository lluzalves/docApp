package com.app.daniel.ifdoc.ui.documents

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseFragment


class HomeFragment : BaseFragment(), MvpHomeView, View.OnClickListener {

    private lateinit var dialog: ProgressDialog
    private var presenter = HomePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.dashboard, container, false)
    }

    override fun connectionStatus(status: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRequestDialog(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissRequestDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showResponse(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLastUploads() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun uploadList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun uploadDocument() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(view: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
