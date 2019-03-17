package com.app.daniel.ifdoc.ui

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.afollestad.materialdialogs.MaterialDialog
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private lateinit var logout: MenuItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        checkPermissions()
        if (savedInstanceState == null) {
            Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.authFragment)
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.document_menu, menu)
        logout = menu.findItem(R.id.action_logout)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item) {
            logout -> {
                val activity = this
                MaterialDialog(this)
                        .icon(R.drawable.ic_warning_black)
                        .title(R.string.warning)
                        .message(R.string.logout_warning)
                        .show {
                            positiveButton(R.string.ok) {
                                PreferenceManager.getDefaultSharedPreferences(baseContext).edit().clear().apply()
                                Navigation.findNavController(activity,R.id.nav_host_fragment).navigate(R.id.authFragment)
                                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                            }
                            negativeButton(R.string.cancel) {
                                it.dismiss()
                            }
                        }
            }
        }
        return super.onOptionsItemSelected(item)

    }
}


