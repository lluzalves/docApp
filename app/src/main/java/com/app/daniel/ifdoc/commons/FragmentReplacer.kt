package com.app.daniel.ifdoc.commons

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity




class FragmentReplacer {


    fun addFragment(fragment: Fragment, fragmentManager: FragmentManager, bundle: Bundle, containerViewId: Int) {
        fragment.arguments = bundle
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun loadInitialActivityFragment(fragment: Fragment, context: Context, containerViewId: Int) {
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment, null).commit()
    }

    fun replaceFragment(nextFragment: Fragment, fragmentManager: FragmentManager, bundle: Bundle, containerViewId: Int) {
        nextFragment.arguments = bundle
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, nextFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
