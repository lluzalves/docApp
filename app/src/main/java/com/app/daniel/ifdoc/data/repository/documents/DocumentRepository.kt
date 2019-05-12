package com.app.daniel.ifdoc.data.repository.documents

import android.app.Application
import com.app.daniel.ifdoc.data.dao.DocsRoomDatabase
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.domain.model.Document
import io.reactivex.Single

class DocumentRepository constructor(application: Application) {
    var database = DocsRoomDatabase.getDatabase(application.applicationContext)
    var dao = database?.documentsDao


    fun allDocuments(): Single<List<Document>> {
        return Single.fromCallable {
            dao?.allDocuments()?.map {
                it.toDocument()
            }
        }
    }

    fun allEdictsDocuments(edictId:String): Single<List<Document>> {
        return Single.fromCallable {
            dao?.allEdictDocuments(edictId)?.map {
                it.toDocument()
            }
        }
    }

    fun retrieveDocument(id: String): Single<Document> {
        return Single.fromCallable {
            dao?.getDocument(id)?.toDocument()
        }
    }

    fun deleteAllDocuments(): Single<Int> {
        return Single.fromCallable{
            dao?.deleteAllDocuments()
        }
    }

    fun insertDocuments(document: List<DocumentEntity>): Single<Array<Long>> {
        return Single.fromCallable {
            dao?.insertDocuments(document)
        }
    }

    fun deleteDocument(documentId: Int): Single<Int> {
        return Single.fromCallable {
            dao?.deleteDocument(documentId)
        }
    }

    fun updateDocument(document: DocumentEntity): Single<Int> {
        return Single.fromCallable {
            dao?.updateDocument(document)
        }
    }
}
