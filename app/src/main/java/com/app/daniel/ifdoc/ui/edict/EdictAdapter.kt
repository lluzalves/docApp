package com.app.daniel.ifdoc.ui.edict

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.domain.model.Edict

class EdictAdapter constructor(var edicts: List<Edict>, val context: Context) : RecyclerView.Adapter<EdictViewHolder>() {

    lateinit var holder: EdictViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EdictViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_edict, null)
        holder = EdictViewHolder(view, context)
        return holder
    }

    override fun onBindViewHolder(holder: EdictViewHolder, position: Int) {
        holder.show(edicts[position])
    }

    override fun getItemCount()
            : Int {
        return edicts.size
    }
}