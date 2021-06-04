package com.djevannn.capstoneproject.ui.upload

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class UploadViewModel : ViewModel() {

    private val mStorage: StorageReference =
        FirebaseStorage.getInstance().getReference("Uploads")

    fun upload(uri: Uri) {
        val mReference = mStorage.child(uri.lastPathSegment!!)
        mReference.putFile(uri)
//        try {
//            mReference.putFile(uri).addOnSuccessListener {
//                    taskSnapshot: UploadTask.TaskSnapshot? -> var url = taskSnapshot!!.downloadUrl
//            }
//        } catch (e: Exception) {
//        }
    }
}