package com.app.daniel.ifdoc.ui.user.register

import com.app.daniel.ifdoc.commons.base.MvpView
import com.app.daniel.ifdoc.data.entities.responses.DocumentResponseEntity

interface MvpAddDocumentView : MvpView{
   fun showSuccessMessage(response : DocumentResponseEntity)
}