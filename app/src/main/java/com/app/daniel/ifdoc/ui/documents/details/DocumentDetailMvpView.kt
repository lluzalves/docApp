package com.app.daniel.ifdoc.ui.documents.details

import com.app.daniel.ifdoc.commons.base.MvpView
import com.app.daniel.ifdoc.domain.model.Document

interface DocumentDetailMvpView : MvpView {
    fun showDocument(document: Document)

}