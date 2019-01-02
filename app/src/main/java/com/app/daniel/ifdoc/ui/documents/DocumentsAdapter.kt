package com.app.daniel.ifdoc.ui.documents

import android.content.Context
import android.view.*
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.application.App
import com.app.daniel.ifdoc.commons.network.OkHttpFactory
import com.app.daniel.ifdoc.commons.network.RetrofitFactory
import com.app.daniel.ifdoc.commons.network.Token
import com.app.daniel.ifdoc.data.entities.responses.BaseResponseEntity
import com.app.daniel.ifdoc.data.services.document.DocumentService
import com.app.daniel.ifdoc.domain.model.Document
import com.app.daniel.ifdoc.domain.repository.documents.DocumentRepository
import com.google.android.material.snackbar.Snackbar
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class DocumentsAdapter constructor(var documents: List<Document>, val context: Context) : RecyclerView.Adapter<DocumentsViewHolder>() {

    lateinit var holder: DocumentsViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_document, null)
        holder = DocumentsViewHolder(view, context)
        return holder
    }

    override fun onBindViewHolder(holder: DocumentsViewHolder, position: Int) {
        holder.show(documents[position])
    }

    override fun getItemCount()
            : Int {
        return documents.size
    }
}