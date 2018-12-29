package com.app.daniel.ifdoc.ui.documents

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseViewHolder
import com.app.daniel.ifdoc.commons.input.StringChecker
import com.app.daniel.ifdoc.commons.view.FragmentReplacer
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.domain.model.Document
import com.app.daniel.ifdoc.ui.documents.details.DocumentDetailFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.card_document.view.*

class DocumentsViewHolder(view: View, val context: Context) : BaseViewHolder<Document>(view), View.OnClickListener {

    lateinit var document: Document
    private val checker = StringChecker()
    val card: LinearLayout by lazy { view.documentCard }
    val documentType: TextView by lazy { view.documentType }
    val documentData: TextView by lazy { view.documentDate }
    val documentStatus: TextView by lazy { view.documentStatus }
    val moreOptions: ImageView by lazy { view.moreOptionsDocument }

    override fun show(document: Document) {

        this.document = document
        card.setOnClickListener(this)
        moreOptions.setOnClickListener(this)
        documentType.text = checker.nullToDash(document.type)
        documentData.text = checker.nullToDash(document.updated_at)
        documentStatus.text = checker.nullToDash(document.validateStatus(document.isValidated))
    }

    override fun onClick(view: View) {
        when (view) {
            moreOptions -> {
                Snackbar.make(view, "Soon", Snackbar.LENGTH_LONG).show()
            }
            card -> {
                val bundle = Bundle()
                bundle.putSerializable(DocumentEntity.NAME, document)
                val fragment = DocumentDetailFragment()
                val manager = (context as AppCompatActivity).supportFragmentManager
                FragmentReplacer().addFragment(fragment, manager, bundle, R.id.container)
            }
        }
    }


}