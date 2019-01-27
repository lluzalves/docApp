package com.app.daniel.ifdoc.ui.documents.details

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.network.Token.getToken
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.domain.model.Document
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_document_details.*


class DocumentDetailFragment : BaseFragment(), DocumentDetailMvpView, View.OnClickListener {

    private lateinit var dialog: ProgressDialog
    private lateinit var document: Document
    private var presenter = DocumentDetailPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        val bundle: Bundle = this.arguments!!
        document = bundle.getSerializable(DocumentEntity.NAME) as Document
        return inflater.inflate(R.layout.fragment_document_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        docDelete.setOnClickListener(this)
        docEdit.setOnClickListener(this)
        presenter.requestDocument(getToken(), documentId = document.id)
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

    override fun showDocument(document: Document) {
        description.text = document.description

        type.text = document.type

        isDocumentValidated(document.isValidated)
    }

    private fun isDocumentValidated(isValidated: String) {
        if (isValidated != "0") {
            docEdit.setTextColor(resources.getColor(R.color.material_grey_600))
            docDelete.setTextColor(resources.getColor(R.color.material_grey_600))
        }
        docStatus.text = document.statusDescription(isValidated)
    }

    override fun showResponse(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
    }

     override fun onClick(view: View?) {
        when (view) {
            docEdit -> {
                val bundle = Bundle()
                bundle.putSerializable(DocumentEntity.NAME, document)
                view?.let { Navigation.findNavController(it).navigate(R.id.addDocumentFragment, bundle) }
            }
            docDelete -> {
                if (document.isValidated == "0") {
                    presenter.deleteDocument(document)
                } else {
                    onError(getString(R.string.unable_to_delete))
                }
            }
        }
    }
}
