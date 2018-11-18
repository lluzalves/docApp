package com.app.daniel.ifdoc.ui.documents

import com.app.daniel.ifdoc.commons.base.MvpView

interface MvpHomeView : MvpView {

    fun showLastUploads()
    fun uploadList()
    fun uploadDocument()
}