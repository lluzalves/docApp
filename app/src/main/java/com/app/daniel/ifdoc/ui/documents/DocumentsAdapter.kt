package com.app.daniel.ifdoc.ui.documents

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.domain.model.Document
import com.app.daniel.ifdoc.domain.model.Edict


class DocumentsAdapter constructor(var documents: List<Document>, val context: Context, val edict: Edict) : RecyclerView.Adapter<DocumentsViewHolder>() {

    lateinit var holder: DocumentsViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_document, null)
        holder = DocumentsViewHolder(view, context)
        holder.edict = edict
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