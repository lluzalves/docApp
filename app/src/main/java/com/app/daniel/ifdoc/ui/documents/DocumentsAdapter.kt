package com.app.daniel.ifdoc.ui.documents

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        holder.moreOptions.setOnClickListener { setupPopupMenu(position) }
    }


    private fun setupPopupMenu(position: Int) {
        val popup = PopupMenu(context, holder.moreOptions)
        popup.inflate(R.menu.document_menu)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            return@OnMenuItemClickListener when (item.itemId) {
                R.id.action_delete -> {
                    deleteDocument(position)
                    true
                }
                R.id.action_edit -> {
                    // Snackbar.make(view, "Details", Snackbar.LENGTH_LONG).show()
                    true
                }
                else -> {
                    false
                }
            }
        })
        popup.show()
    }

    private fun deleteDocument(position: Int) {
        val client = OkHttpFactory()
                .prepareClientWithToken(Token.getToken())
        RetrofitFactory().setRetrofit(client)
                .create(DocumentService::class.java)
                .deleteDocument(documents[position].id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<BaseResponseEntity> {
                    override fun onSuccess(response: BaseResponseEntity) {
                        Snackbar.make(holder.moreOptions, response.message, Snackbar.LENGTH_LONG).show()
                        DocumentRepository(App.appInstance).deleteDocument(documents[position].id)

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        Snackbar.make(holder.moreOptions, e.localizedMessage, Snackbar.LENGTH_LONG).show()
                    }
                })
    }

    override fun getItemCount()
            : Int {
        return documents.size
    }
}