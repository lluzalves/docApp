package com.app.daniel.ifdoc.ui

import android.os.Bundle
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseActivity
import com.app.daniel.ifdoc.commons.view.FragmentReplacer
import com.app.daniel.ifdoc.ui.user.auth.AuthFragment


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermissions()
        if (savedInstanceState == null) {
            var fragment = AuthFragment()
            FragmentReplacer().loadInitialActivityFragment(fragment, this, R.id.container)
        }
    }
}

