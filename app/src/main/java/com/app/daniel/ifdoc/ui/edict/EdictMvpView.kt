package com.app.daniel.ifdoc.ui.edict

import com.app.daniel.ifdoc.commons.base.MvpView
import com.app.daniel.ifdoc.domain.model.Edict

interface EdictMvpView : MvpView {
 fun showEdicts(edicts : List<Edict>)
 fun noEdictAvailable()
}