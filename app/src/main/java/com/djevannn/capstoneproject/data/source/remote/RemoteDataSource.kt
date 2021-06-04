package com.djevannn.capstoneproject.data.source.remote

import android.util.Log
import com.djevannn.capstoneproject.data.source.remote.network.ApiResponse
import com.djevannn.capstoneproject.data.source.remote.response.BookResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getBookList(): Flow<ApiResponse<List<BookResponse>>> {
        return flow {
            try {
                val response = apiService.getBookList()

                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                    Log.e("RemoteDataSource", "Empty")
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

//    fun getBookList(): LiveData<ApiResponse<List<BookResponse>>> {
//        val result =
//            MutableLiveData<ApiResponse<List<BookResponse>>>()
//
//        val client = apiService.getBookList()
//        client.enqueue(object : Callback<List<BookResponse>> {
//            override fun onResponse(
//                call: Call<List<BookResponse>>,
//                response: Response<List<BookResponse>>
//            ) {
//                if (response.isSuccessful) {
//                    result.value =
//                        ApiResponse.Success(response.body()!!)
//                } else {
//                    result.value =
//                        ApiResponse.Error(
//                            response.message()
//                        )
//                }
//            }
//
//            override fun onFailure(
//                call: Call<List<BookResponse>>,
//                t: Throwable
//            ) {
//                Log.e(
//                    "RemoteDataSource",
//                    "onFailure: ${t.message.toString()}"
//                )
//            }
//
//        })
//
//        return result
//    }

//    fun postBook(
//        sourceFile: File,
//        uploadedFileName: String? = null
//    ) {
//        Thread {
//            val mimeType = getMimeType(sourceFile);
//            if (mimeType == null) {
//                Log.e("file error", "Not able to get mime type")
//                return@Thread
//            }
//            val fileName: String =
//                uploadedFileName ?: sourceFile.name
//
//            try {
//                val requestBody: RequestBody =
//                    MultipartBody.Builder()
//                        .setType(MultipartBody.FORM)
//                        .addFormDataPart(
//                            "uploaded_file",
//                            fileName,
//                            sourceFile.asRequestBody(mimeType.toMediaTypeOrNull())
//                        )
//                        .build()
//
//                // change here
//                val request: Request =
//                    Request.Builder().url("serverURL")
//                        .post(requestBody)
//                        .build()
//
//                val client = OkHttpClient()
//                val response: okhttp3.Response =
//                    client.newCall(request).execute()
//
//                if (response.isSuccessful) {
//                    Log.d(
//                        "File upload",
//                        "success, path"
//                    )
//                } else {
//                    Log.e("File upload", "failed")
//                }
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//                Log.e("File upload", "failed")
//            }
//        }.start()
//    }
//
//    // url = file path or whatever suitable URL you want.
//    private fun getMimeType(file: File): String? {
//        var type: String? = null
//        val extension = MimeTypeMap.getFileExtensionFromUrl(file.path)
//        if (extension != null) {
//            type = MimeTypeMap.getSingleton()
//                .getMimeTypeFromExtension(extension)
//        }
//        return type
//    }

}