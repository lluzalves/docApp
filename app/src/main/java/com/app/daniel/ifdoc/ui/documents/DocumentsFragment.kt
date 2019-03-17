package com.app.daniel.ifdoc.ui.documents

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
import com.app.daniel.ifdoc.domain.model.Document
import kotlinx.android.synthetic.main.fragment_home.*


class DocumentsFragment : BaseFragment(), DocumentsMvpView, View.OnClickListener {

    private lateinit var dialog: ProgressDialog
    private lateinit var adapter: DocumentsAdapter
    private var presenter = DocumentsPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.requestUserDocuments(Token.getToken())
        createDocument.setOnClickListener(this)

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


    override fun retrieveFetchedDocuments() {
        presenter.retrieveFetchedDocuments()
    }

    override fun emptyDocuments() {
        emptyHome.isVisible = true
    }

    override fun showDocuments(documents: List<Document>) {
        if (documents.isNullOrEmpty()) {
            emptyHome.isVisible = true
        } else {
            emptyHome.isVisible = false
            context?.let {
                adapter = DocumentsAdapter(documents, it)
            }
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            AllDocumentsRecycler.layoutManager = staggeredGridLayoutManager
            context?.let { DivisorItens(it) }?.let { AllDocumentsRecycler.addItemDecoration(it) }
            registerForContextMenu(AllDocumentsRecycler)
            AllDocumentsRecycler.adapter = adapter
        }
    }

    override fun showResponse(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLastUploads() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onClick(view: View?) {
        when (view) {
            createDocument -> {
                nextScreen(R.id.addDocumentFragment)
            }
        }
    }
}
