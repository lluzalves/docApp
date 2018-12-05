package com.app.daniel.ifdoc.ui.documents

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseViewHolder
import com.app.daniel.ifdoc.commons.input.StringChecker
import com.app.daniel.ifdoc.commons.network.OkHttpFactory
import com.app.daniel.ifdoc.commons.network.RetrofitFactory
import com.app.daniel.ifdoc.commons.network.Token
import com.app.daniel.ifdoc.data.entities.responses.BaseResponseEntity
import com.app.daniel.ifdoc.data.services.document.DocumentService
import com.app.daniel.ifdoc.domain.model.Document
import com.google.android.material.snackbar.Snackbar
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.card_document.view.*

class HomeViewHolder(view: View, val context: Context) : BaseViewHolder<Document>(view) {

    private lateinit var document: Document

    val card: LinearLayout by lazy { view.documentCard }
    val documentType: TextView by lazy { view.documentType }
    val documentData: TextView by lazy { view.documentDate }
    val documentStatus: TextView by lazy { view.documentStatus }
    val moreOptions: ImageView by lazy { view.moreOptionsDocument }

    override fun show(document: Document) {
        documentType.text = StringChecker().nullToDash(document.type)
        documentData.text = StringChecker().nullToDash(document.updated_at)
        documentStatus.text = StringChecker().nullToDash(document.validateStatus(document.isValidated))
    }
 }