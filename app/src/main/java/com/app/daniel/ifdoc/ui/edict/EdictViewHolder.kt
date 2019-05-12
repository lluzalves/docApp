package com.app.daniel.ifdoc.ui.edict

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseViewHolder
import com.app.daniel.ifdoc.commons.input.StringChecker
import com.app.daniel.ifdoc.domain.model.Edict
import kotlinx.android.synthetic.main.card_edict.view.*

class EdictViewHolder(view: View, val context: Context) : BaseViewHolder<Edict>(view), View.OnClickListener {

    lateinit var edict: Edict
    private val checker = StringChecker()
    private val card: ConstraintLayout by lazy { view.cardEdict }
    private val edictTitle: TextView by lazy { view.edictName }
    private val edictDate: TextView by lazy { view.edictAvailableDate }

    override fun show(edict: Edict) {

        this.edict = edict
        card.setOnClickListener(this)
        edictTitle.text = checker.nullToDash(edict.title)
        edictDate.text = checker.nullToDash(edict.end_at)
    }

    override fun onClick(view: View) {
        when (view) {
            card -> {
                val bundle = Bundle()
                bundle.putSerializable("edict",edict)
                Navigation.findNavController(view).navigate(R.id.documentsFragment, bundle)
            }
        }
    }


}