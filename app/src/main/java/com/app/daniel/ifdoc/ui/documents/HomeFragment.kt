package com.app.daniel.ifdoc.ui.documents

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.view.FragmentReplacer
import com.app.daniel.ifdoc.domain.model.Document
import com.app.daniel.ifdoc.ui.documents.add.AddDocumentFragment
import kotlinx.android.synthetic.main.fragment_home.*
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.daniel.ifdoc.commons.view.DivisorItens


class HomeFragment : BaseFragment(), MvpHomeView, View.OnClickListener {

    private lateinit var dialog: ProgressDialog
    private lateinit var adapter: HomeAdapter
    private var presenter = HomePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.requestUserDocuments(getToken())
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


    override fun retrieveFetchedDocuments() {
        presenter.retrieveFetchedDocuments()
    }

    override fun emptyDocuments() {
        emptyHome.isVisible = true
        pendingDocumentsLayout.isVisible = true
        horizontalScrollView.isVisible = true
    }

    override fun showDocuments(documents: List<Document>) {
        if (documents.isNullOrEmpty()) {
            emptyHome.isVisible = true
            pendingDocumentsLayout.isVisible = true
            horizontalScrollView.isVisible = true
        } else {
            emptyHome.isVisible = false
            context?.let {
                adapter = HomeAdapter(documents, it)
            }
            var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            AllDocumentsRecycler.layoutManager = staggeredGridLayoutManager
            context?.let { DivisorItens(it) }?.let { AllDocumentsRecycler.addItemDecoration(it) }
            registerForContextMenu(AllDocumentsRecycler)
            AllDocumentsRecycler.adapter = adapter
        }
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
