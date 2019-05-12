package com.app.daniel.ifdoc.commons.base

import android.Manifest.permission.*
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.app.daniel.ifdoc.commons.application.permissions.PermissionsHelper
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.distribute.Distribute
import com.app.daniel.ifdoc.commons.distro.MyDistributeListener

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var logout: MenuItem

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Distribute.setListener(MyDistributeListener())
        AppCenter.start(application, "acfedbc8-11ee-42c6-ae7e-e331416efc2c", Analytics::class.java, Crashes::class.java, Distribute::class.java)
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
