package com.app.daniel.ifdoc.ui.documents

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.view.FragmentReplacer
import com.app.daniel.ifdoc.ui.documents.add.AddDocumentFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment(), MvpHomeView, View.OnClickListener {

    private lateinit var dialog: ProgressDialog
    private var presenter = HomePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.checkUserDocuments(getToken())
        createDocument.setOnClickListener(this)
    }

    override fun connectionStatus(status: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRequestDialog(message: String) {
        dialog = ProgressDialog.show(context, getString(R.string.message_wait), message)
    }

    override fun dismissRequestDialog() {
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
    }

    override fun showDocuments() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showResponse(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLastUploads() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onClick(view: View?) {
        when (view) {
            createDocument -> {
                var fragment = AddDocumentFragment()
                val bundle = Bundle()
                bundle.putInt("view_to_circular_animation_x", createDocument.x.toInt())
                bundle.putInt("view_to_circular_animation_y", createDocument.y.toInt())
                fragmentManager?.let { manager -> FragmentReplacer().addFragment(fragment, manager, bundle, R.id.container) }
            }
        }
    }
}
