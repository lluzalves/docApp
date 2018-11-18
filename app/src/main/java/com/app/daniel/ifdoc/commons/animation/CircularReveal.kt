package com.app.daniel.ifdoc.commons.animation

import android.animation.Animator
import android.view.View
import android.view.ViewAnimationUtils

object CircularReveal {

    fun setupCircularReveal(view: View, centerX: Int, centerY: Int) {
        view.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                v.removeOnLayoutChangeListener(this)
                val width = (view.parent as View).width
                val height = (view.parent as View).height
                val radius = (Math.max(width, height) + Math.max(width - centerX, height - centerY)).toFloat()
                var anim: Animator
                anim = ViewAnimationUtils.createCircularReveal(v, centerX, centerY, 0.0F, radius)
                anim.duration = 600
                anim.start()
            }
        })
    }
}