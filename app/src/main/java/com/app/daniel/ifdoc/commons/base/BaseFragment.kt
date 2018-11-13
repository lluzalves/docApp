package com.app.daniel.ifdoc.commons.base

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), IOnBackPressed {

    abstract val name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onResume() {
        super.onResume()
        activity!!.title = this.name
    }

}