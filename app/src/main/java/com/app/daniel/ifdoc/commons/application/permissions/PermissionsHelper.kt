package com.app.daniel.ifdoc.commons.application.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class PermissionsHelper {

    fun grant(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context as Activity,
                        arrayOf(Manifest.permission.CAMERA),
                        1)
                return
            }
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context as Activity,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        1)
                return
            }
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context as Activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1)
                return
            }
        }
    }
}

