package com.app.daniel.ifdoc.commons.base

interface MvpView {

    fun checkConnectionStatus(status : Boolean)

    fun showRequestDialog(message : String)

    fun dismissRequestDialog()

    fun onError(message: String)

    fun showResponse(message: String)

    fun nextScreen(flowIntention: Int)
}
