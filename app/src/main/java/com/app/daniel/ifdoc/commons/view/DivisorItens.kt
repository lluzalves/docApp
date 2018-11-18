package com.app.daniel.ifdoc.commons.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.app.daniel.ifdoc.R


class DivisorItens : RecyclerView.ItemDecoration() {

    private lateinit var divisorItem: Drawable

    fun DivisorItens(context: Context) {
        divisorItem = getDrawable(context, R.drawable.divisor)!!
    }

    override fun onDrawOver(c: Canvas, recyclerViewItem: RecyclerView) {
        val left = recyclerViewItem.paddingLeft
        val right = recyclerViewItem.width - recyclerViewItem.paddingRight
        val childCount = recyclerViewItem.childCount

        for (i in 0 until childCount) {
            val child = recyclerViewItem.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + divisorItem.intrinsicHeight
            divisorItem.setBounds(left, top, right, bottom)
            divisorItem.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
            divisorItem.draw(c)
        }
    }
}