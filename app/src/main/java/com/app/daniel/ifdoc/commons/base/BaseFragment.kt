package com.app.daniel.ifdoc.commons.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.afollestad.materialdialogs.MaterialDialog
import com.app.daniel.ifdoc.R
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment : Fragment(), MvpView {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
    }

    override fun onError(message: String) {
    }

    override fun nextScreen(flowIntention: Int) {
        view?.let { Navigation.findNavController(it).navigate(flowIntention) }
    }

}