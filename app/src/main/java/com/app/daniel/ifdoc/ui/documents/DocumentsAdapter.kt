package com.app.daniel.ifdoc.ui.documents

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.domain.model.Document

class DocumentsAdapter constructor(var documents: List<Document>, val context: Context) : RecyclerView.Adapter<DocumentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_document, null)
        return DocumentsViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: DocumentsViewHolder, position: Int) {
        holder.show(documents[position])
    }

    override fun getItemCount(): Int {
        return documents.size
    }

}