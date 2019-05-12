package com.app.daniel.ifdoc.ui.documents.details

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.afollestad.materialdialogs.MaterialDialog
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.network.Token.getToken
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.domain.model.Document
import com.app.daniel.ifdoc.domain.model.Edict
import kotlinx.android.synthetic.main.fragment_document_details.*


class DocumentDetailFragment : BaseFragment(), DocumentDetailMvpView, View.OnClickListener {

    private lateinit var dialog: ProgressDialog
    private lateinit var document: Document
    private lateinit var edict: Edict
    private var presenter = DocumentDetailPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        val bundle: Bundle = this.arguments!!
        edict = arguments?.getSerializable("edict") as Edict
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

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val logout = menu.findItem(R.id.action_logout)
        logout.isVisible = false
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
        docDetailsView.isVisible = true
    }

    private fun isDocumentValidated(isValidated: String) {
        if (isValidated != "0") {
            docEdit.setOnClickListener(null)
            docEdit.setTextColor(resources.getColor(R.color.material_grey_600))
            docDelete.setTextColor(resources.getColor(R.color.material_grey_600))
        }
        docStatus.text = document.statusDescription(isValidated)
    }

    override fun showResponse(message: String) {
        view?.let {
            view?.context?.let {
                MaterialDialog(it).icon(R.drawable.ic_done)
                        .title(R.string.warning)
                        .message(null, getString(R.string.document_deleted), false, 1F)
                        .show {
                            positiveButton(R.string.ok, click = MaterialDialog::dismiss)
                        }.positiveButton {
                            Navigation.findNavController(view!!).popBackStack()
                        }
            }
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            docEdit -> {
                val bundle = Bundle()
                bundle.putSerializable(DocumentEntity.NAME, document)
                bundle.putSerializable("edict",edict)
                view?.let { Navigation.findNavController(it).navigate(R.id.action_documentDetailFragment_to_addDocumentFragment, bundle) }
            }
            docDelete -> {
                if (document.isValidated == "0") {
                    presenter.deleteDocument(document)
                } else {
                    view?.context?.let {
                        MaterialDialog(it).icon(R.drawable.ic_warning_black)
                                .title(R.string.unable_to_delete)
                                .message(null, getString(R.string.unable_to_delete), false, 1F)
                                .show {
                                    positiveButton(R.string.ok, click = MaterialDialog::dismiss)
                                }.positiveButton {
                                    Navigation.findNavController(view!!).popBackStack()
                                }
                    }
                }
            }
        }
    }
}
