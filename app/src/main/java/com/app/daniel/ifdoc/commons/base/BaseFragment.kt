package com.app.daniel.ifdoc.commons.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment : Fragment(), MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onError(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
    }

    override fun nextScreen(flowIntention: Int) {
        view?.let { Navigation.findNavController(it).navigate(flowIntention) }
    }

}