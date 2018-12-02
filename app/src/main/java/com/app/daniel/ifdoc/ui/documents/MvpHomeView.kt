package com.app.daniel.ifdoc.ui.documents

import com.app.daniel.ifdoc.commons.base.MvpView
import com.app.daniel.ifdoc.domain.model.Document

interface MvpHomeView : MvpView {

    fun showLastUploads()

    fun retrieveFetchedDocuments()

    fun emptyDocuments()

    fun showDocuments(documents: List<Document>)

}