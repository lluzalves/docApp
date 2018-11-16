package com.app.daniel.ifdoc.commons.base

interface MvpView {

    fun connectionStatus(status : Boolean)

    fun showRequestDialog(message : String)

    fun dismissRequestDialog()

    fun onError(message: String)

    fun previousScreen()

    fun nextScreen(flowIntention: Int)
}
