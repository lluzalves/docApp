package com.app.daniel.ifdoc.domain.repository.documents

import android.app.Application
import com.app.daniel.ifdoc.data.dao.DocsRoomDatabase
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.domain.model.Document
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class DocumentRepository constructor(application: Application) {
    var database = DocsRoomDatabase.getDatabase(application)
    var dao = database?.documentsDao


    fun allDocuments(): List<Document>? {
        return dao?.allDocuments()?.map {
            it.toDocument()
        }
    }

    fun insertDocument(document: Document) {
        Completable.fromAction {
            dao?.insertDocument(document as DocumentEntity)
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun deleteDocument(document: Document) {
        Completable.fromAction {
            dao?.deleteDocument(document as DocumentEntity)
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun updateDocument(document: Document) {
        Completable.fromAction {
            dao?.updateDocument(document as DocumentEntity)
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }
}
