package com.app.daniel.ifdoc.ui.edict


import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible

import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.network.Token
import com.app.daniel.ifdoc.domain.model.Edict
import kotlinx.android.synthetic.main.fragment_edict.*
import kotlinx.android.synthetic.main.fragment_home.*

class EdictFragment : BaseFragment(), EdictMvpView {
    private lateinit var dialog: ProgressDialog
    private var presenter = EdictPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edict, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // presenter.requestEdictsForUser(Token.getToken())
    }

    override fun checkConnectionStatus(status: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun showRequestDialog(message: String) {
        dialog = ProgressDialog.show(context, getString(R.string.message_wait), message)
    }

    override fun dismissRequestDialog() {
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
    }

    override fun showResponse(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEdicts(edicts: List<Edict>) {
        updateUi(isEmpty = false)
    }

    override fun noEdictAvailable() {
        updateUi(isEmpty = true)
    }

    private fun updateUi(isEmpty: Boolean) {
        when {
            isEmpty -> {
                scrollEdicts.isVisible = false
                emptyEdicts.isVisible = true
            }
            else ->{
                scrollEdicts.isVisible = true
                emptyEdicts.isVisible = false
            }
        }
    }

}
