package com.app.daniel.ifdoc.data.services.document

import com.app.daniel.ifdoc.commons.api.Constants
import com.app.daniel.ifdoc.data.entities.DocumentResponseEntity
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface DocumentService {

    @GET(Constants.Api.API_URL + Constants.Endpoint.DOCUMENTS)
    fun myDocuments(): Single<DocumentResponseEntity>

    @Multipart
    @POST(Constants.Api.API_URL + Constants.Endpoint.DOCUMENTS)
    fun createDocument(@Part("description") description: RequestBody,
                       @Part file: MultipartBody.Part,
                       @Part("type") documentType: RequestBody): Single<DocumentResponseEntity>

}