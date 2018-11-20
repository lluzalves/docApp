package  com.app.daniel.ifdoc.ui.documents.add

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.core.content.FileProvider
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.input.image.ImageDecoder
import com.app.daniel.ifdoc.commons.network.NetworkChecker
import com.app.daniel.ifdoc.ui.user.register.AddDocumentPresenter
import com.app.daniel.ifdoc.ui.user.register.MvpAddDocumentView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_create_document.*
import java.io.File


class AddDocumentFragment : BaseFragment(), MvpAddDocumentView, View.OnClickListener {

    private lateinit var dialog: ProgressDialog
    private var presenter = AddDocumentPresenter()
    private val CAMERA_REQUEST = 1888
    private lateinit var folder: File
    private lateinit var pictureFile: File

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.fragment_create_document, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendDocument.setOnClickListener(this)
        takePicture.setOnClickListener(this)
        folder = presenter.setFolder()
        setSpinnerData()
    }

    private fun setSpinnerData() {
        val adapter = ArrayAdapter.createFromResource(context, R.array.document_type, R.layout.spinner_layout)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        //   spinner.setOnItemSelectedListener(this)
    }

    override fun connectionStatus(isRegistered: Boolean) {
        if (isRegistered) {
            //   presenter.createDocument(getToken())
        } else {
            view?.let { Snackbar.make(it, getString(R.string.register_failure), Snackbar.LENGTH_LONG).show() }
        }
    }

    private fun checkNetwork(context: Context) {
        if (NetworkChecker(context).isNetworkActive()) {
            presenter.checkInternetConnection(context)
        } else {
            view?.let { Snackbar.make(it, getString(R.string.no_connection), Snackbar.LENGTH_LONG).show() }
        }
    }

    override fun showRequestDialog(message: String) {
        dialog = ProgressDialog.show(context, getString(R.string.message_wait), message)
    }

    override fun dismissRequestDialog() {
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
    }

    override fun showResponse(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
        fragmentManager?.popBackStack()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            insertPhoto(attachment, pictureFile.absolutePath)
        }
    }

    private fun insertPhoto(imageView: ImageView, absolutePath: String) {
        val bitmap = ImageDecoder().decodeSampleBitmapFromUri(absolutePath, 100, 100)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageBitmap(bitmap)
    }

    override fun onClick(view: View) {
        when (view) {
            sendDocument -> {
                val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                checkNetwork(view.context)
            }
            takePicture -> {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                pictureFile = File(folder, System.currentTimeMillis().toString() + ".png")
                val savedPicture = activity?.let { FileProvider.getUriForFile(it, it.applicationContext.packageName + ".provider", pictureFile) }
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, savedPicture)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
        }
    }

    override fun showAttachment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
