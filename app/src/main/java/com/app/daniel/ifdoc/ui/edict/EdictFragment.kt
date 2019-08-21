package com.app.daniel.ifdoc.ui.edict


import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.network.Token
import com.app.daniel.ifdoc.commons.view.DivisorItens
import com.app.daniel.ifdoc.domain.model.Edict
import kotlinx.android.synthetic.main.fragment_edict.*

class EdictFragment : BaseFragment(), EdictMvpView {
    private lateinit var dialog: ProgressDialog
    private var presenter = EdictPresenter()
    private lateinit var adapter: EdictAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.fragment_edict, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.requestEdictsForUser(Token.getToken())
    }

    override fun checkConnectionStatus(status: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun showRequestDialog(message: String) {
        dialog = ProgressDialog.show(context, getString(R.string.message_wait), message)
    }

    override fun dismissRequestDialog() {
        if (::dialog.isInitialized) {
            dialog.dismiss()
        }
    }

    override fun showResponse(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEdicts(edicts: List<Edict>) {
        dismissRequestDialog()
        updateUi(isEmpty = false)
        if (edicts.isNullOrEmpty()) {
            noEdictAvailable()
        } else {
            updateUi(false)
            context?.let {
                adapter = EdictAdapter(edicts, it)
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                AllEdicts.layoutManager = staggeredGridLayoutManager
                context?.let { DivisorItens(it) }?.let { AllEdicts.addItemDecoration(it) }
                registerForContextMenu(AllEdicts)
                AllEdicts.adapter = adapter
            }
        }
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
            else -> {
                scrollEdicts.isVisible = true
                emptyEdicts.isVisible = false
            }
        }
    }

}
