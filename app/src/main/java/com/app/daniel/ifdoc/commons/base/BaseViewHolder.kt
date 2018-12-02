package com.app.daniel.ifdoc.commons.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<Data : Any> (view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

    abstract fun show(data : Data)
}