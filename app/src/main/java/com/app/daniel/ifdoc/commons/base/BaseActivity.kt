package com.app.daniel.ifdoc.commons.base

import android.Manifest.permission.*
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.app.daniel.ifdoc.commons.application.permissions.PermissionsHelper


abstract class BaseActivity : AppCompatActivity() {

    override fun onBackPressed() {
        val count = fragmentManager.backStackEntryCount

        if (count == 0) {
            finish()
        } else {
            fragmentManager.popBackStack()
        }
    }


    fun checkPermissions() {
        PermissionsHelper().grant(this)
    }


    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionsMap = HashMap<String, Int>()
        for ((index, permission) in permissions.withIndex()) {
            permissionsMap[permission] = grantResults[index]
        }

        if (permissionsMap[READ_EXTERNAL_STORAGE] != 0 || permissionsMap[WRITE_EXTERNAL_STORAGE] != 0 || permissionsMap[CAMERA] != 0) {
            PermissionsHelper().grant(this)
        } else {
            return
        }
    }
}