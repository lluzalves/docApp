package com.app.daniel.ifdoc.ui

import android.os.Bundle
import androidx.navigation.Navigation
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseActivity



class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermissions()
        if (savedInstanceState == null) {
            Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.authFragment)
        }
    }
}

