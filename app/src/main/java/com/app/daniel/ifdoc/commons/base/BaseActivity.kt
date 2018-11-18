package com.app.daniel.ifdoc.commons.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onBackPressed() {
        val count = fragmentManager.backStackEntryCount

        if (count == 0) {
            finish()
        } else {
            fragmentManager.popBackStack()
        }
    }
}