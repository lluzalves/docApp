package com.app.daniel.ifdoc.domain.usecases

import com.app.daniel.ifdoc.domain.model.Document
import com.app.daniel.ifdoc.domain.repository.documents.DocumentRepository
import io.reactivex.Single


class LoadValidDocumentsUseCase constructor(val documentRepository: DocumentRepository){

    fun buildUseCaseSingle(id: Int) : Single<List<Document>> {
        return documentRepository.allDocuments().map {
             it.filter { it.userId == id }
        }
    }

}