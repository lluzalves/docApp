package com.app.daniel.ifdoc.ui.documents

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseViewHolder
import com.app.daniel.ifdoc.commons.input.StringChecker
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.domain.model.Document
import com.app.daniel.ifdoc.domain.model.Edict
import kotlinx.android.synthetic.main.card_document.view.*

class DocumentsViewHolder(view: View, val context: Context) : BaseViewHolder<Document>(view), View.OnClickListener {

    lateinit var document: Document
    lateinit var edict: Edict
    private val checker = StringChecker()
    val card: LinearLayout by lazy { view.documentCard }
    val documentType: TextView by lazy { view.documentType }
    val documentData: TextView by lazy { view.documentDate }
    val documentStatus: TextView by lazy { view.documentStatus }

    override fun show(document: Document) {

        this.document = document
        card.setOnClickListener(this)
        documentType.text = checker.nullToDash(document.type)
        documentData.text = checker.nullToDash(document.updated_at)
        documentStatus.text = checker.nullToDash(document.statusDescription(document.isValidated))
    }

    override fun onClick(view: View) {
        when (view) {
            card -> {
                val bundle = Bundle()
                bundle.putSerializable(DocumentEntity.NAME, document)
                bundle.putSerializable("edict", edict)
                Navigation.findNavController(view).navigate(R.id.action_documentsFragment_to_documentDetailFragment, bundle)
            }
        }
    }


}