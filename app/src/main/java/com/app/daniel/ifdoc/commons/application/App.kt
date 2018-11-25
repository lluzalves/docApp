package com.app.daniel.ifdoc.commons.application

import android.app.Application

class App : Application() {

    override fun onCreate() {
        appInstance = this
        super.onCreate()
    }

    companion object {
        lateinit var appInstance: App
    }
}