package  com.app.daniel.ifdoc.ui.documents.add

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.app.daniel.ifdoc.R
import com.app.daniel.ifdoc.commons.base.BaseFragment
import com.app.daniel.ifdoc.commons.input.image.ImageDecoder
import com.app.daniel.ifdoc.commons.network.NetworkChecker
import com.app.daniel.ifdoc.commons.network.Token.getToken
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.data.entities.responses.DocumentResponseEntity
import com.app.daniel.ifdoc.domain.model.Document
import com.app.daniel.ifdoc.ui.user.register.MvpAddDocumentView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_create_document.*
import java.io.File
import java.io.FileNotFoundException


class AddDocumentFragment : BaseFragment(), MvpAddDocumentView, View.OnClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var dialog: ProgressDialog
    private lateinit var camera: ImageView
    private lateinit var attach: ImageView
    private lateinit var attachmentDialog: Dialog
    private lateinit var type: String
    private var document: Document? = null
    private var presenter = AddDocumentPresenter()
    private val cameraRequest = 1888
    private val attachmentRequest = 1887
    private lateinit var folder: File
    private lateinit var pictureFile: File
    private lateinit var savedPicture: Uri

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.fragment_create_document, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (this.arguments != null) {
            val bundle: Bundle = this.arguments!!
            document = bundle.getSerializable(DocumentEntity.NAME) as Document
            if (document != null) {
                labelDocument.text = getString(R.string.edit_document)
                docDescription.setText(document!!.description)
            }
        }
        sendDocument.setOnClickListener(this)
        takePicture.setOnClickListener(this)
        folder = presenter.setFolder()
        setSpinnerData()
    }

    private fun setSpinnerData() {
        val adapter = ArrayAdapter.createFromResource(context, R.array.document_type, R.layout.spinner_layout)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    override fun checkConnectionStatus(isRegistered: Boolean) {
        if (isRegistered) {
            var id = ""
            if(document!=null){
               id = document!!.id.toString()
            }
            activity?.contentResolver?.getType(savedPicture)?.let {
                    presenter.createDocument(getToken(), docDescription.text.toString(), pictureFile, it, type,id)
            }
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                cameraRequest -> insertFromCamera(attachment, pictureFile.absolutePath)
                attachmentRequest -> data?.let { insertAttachment(it) }
            }
        }
    }

    private fun insertAttachment(data: Intent) {
        try {
            savedPicture = data.data
            pictureFile = File(getPath(savedPicture))
            val imageStream = activity?.contentResolver?.openInputStream(savedPicture)
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            attachment.setImageBitmap(selectedImage)
            attachment.setOnClickListener(this)
        } catch (e: FileNotFoundException) {
            Log.d("ifdoc", "Failed to attach file into imageview ".plus(e.localizedMessage))
            view?.let { Snackbar.make(it, getString(R.string.fail_to_attach_image), Snackbar.LENGTH_LONG).show() }
        }

    }

    private fun insertFromCamera(imageView: ImageView, absolutePath: String) {
        val bitmap = ImageDecoder().decodeSampleBitmapFromUri(absolutePath, 200, 200)
        imageView.scaleType = ImageView.ScaleType.CENTER
        imageView.setImageBitmap(bitmap)
    }

    override fun onClick(view: View) {
        when (view) {
            sendDocument -> {
                if (docDescription.text.toString().isEmpty() || !::pictureFile.isInitialized || !::savedPicture.isInitialized) {
                    view.let { Snackbar.make(it, getString(R.string.all_fields_are_required), Snackbar.LENGTH_LONG).show() }
                } else {
                    val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                    checkNetwork(view.context)
                }
            }
            takePicture -> {
                showAttachmentDialog()
            }
            attach -> {
                attachmentDialog.dismiss()
                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                startActivityForResult(photoPickerIntent, attachmentRequest)
            }
            camera -> {
                attachmentDialog.dismiss()
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                pictureFile = File(folder, System.currentTimeMillis().toString() + ".png")
                savedPicture = activity?.let { FileProvider.getUriForFile(it, it.applicationContext.packageName + ".provider", pictureFile) }!!
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, savedPicture)
                startActivityForResult(cameraIntent, cameraRequest)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            spinner.id -> {
                type = parent.selectedItem.toString()
            }
        }
    }

    private fun showAttachmentDialog() {
        attachmentDialog = Dialog(context)
        attachmentDialog.setContentView(R.layout.dialog_attachment_options)
        camera = attachmentDialog.findViewById(R.id.camera) as ImageView
        attach = attachmentDialog.findViewById(R.id.attach) as ImageView
        camera.setOnClickListener(this)
        attach.setOnClickListener(this)
        attachmentDialog.show()
    }

    private fun getPath(uri: Uri): String {
        val result: String
        val cursor = activity?.contentResolver?.query(uri, null, null, null, null)
        if (cursor == null) {
            result = uri.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

    override fun showSuccessMessage(response: DocumentResponseEntity) {
        view?.let { Snackbar.make(it, response.message, Snackbar.LENGTH_LONG).show() }
        presenter.updateLocalDatabase(response.documents)
    }
}
