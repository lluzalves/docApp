package com.app.daniel.ifdoc.commons.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.app.daniel.ifdoc.R


class DivisorItens constructor(val context: Context) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, recyclerViewItem: RecyclerView) {
        val left = recyclerViewItem.paddingLeft
        val right = recyclerViewItem.width - recyclerViewItem.paddingRight
        val childCount = recyclerViewItem.childCount

        var divisorItem : Drawable = getDrawable(context, R.drawable.divisor)!!
        for (i in 0 until childCount) {
            val child = recyclerViewItem.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + divisorItem.intrinsicHeight
            divisorItem.let {
                it.setBounds(left, top, right, bottom)
                it.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
                it.draw(c)
            }
        }
    }
}