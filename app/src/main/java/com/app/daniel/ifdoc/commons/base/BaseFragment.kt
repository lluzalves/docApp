package com.app.daniel.ifdoc.commons.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.daniel.ifdoc.commons.animation.CircularReveal


abstract class BaseFragment : Fragment(), IOnBackPressed, MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

     override fun onBackPressed(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun previousScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun nextScreen(flowIntention: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}