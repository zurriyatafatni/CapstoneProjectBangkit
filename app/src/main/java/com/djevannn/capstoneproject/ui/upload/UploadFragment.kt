package com.djevannn.capstoneproject.ui.upload

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.djevannn.capstoneproject.databinding.FragmentUploadBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream


class UploadFragment : Fragment() {

    // private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()

        binding.btnUpload.setOnClickListener {
            val intent = Intent()
                .setType("*/*")
//                intent.type="application/pdf"
                .setAction(Intent.ACTION_GET_CONTENT)

            startActivityForResult(
                Intent.createChooser(
                    intent,
                    "Select a file"
                ), 111
            )
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val uri =
                data?.data //The uri with the location of the file
            // pdfView.fromUri(selectedFile).load() // Show the selected file
             val pdf = getStringPdf(uri!!)
            // upload goes here
            Toast.makeText(
                context,
                pdf.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun getStringPdf(filepath: Uri): String? {
        var inputStream: InputStream? = null
        var byteArrayOutputStream = ByteArrayOutputStream()
        try {
            inputStream =
                context?.contentResolver?.openInputStream(filepath)
            val buffer = ByteArray(1024)
            byteArrayOutputStream = ByteArrayOutputStream()
            var bytesRead: Int
            while (inputStream!!.read(buffer)
                    .also { bytesRead = it } != -1
            ) {
                byteArrayOutputStream.write(buffer, 0, bytesRead)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        val pdfByteArray: ByteArray =
            byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(pdfByteArray, Base64.DEFAULT)
    }

    private fun setupActionBar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.appBarMain.toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = ""
        binding.appBarMain.tvAppTitle.text = "Upload"
    }
}