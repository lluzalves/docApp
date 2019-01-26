package com.app.daniel.ifdoc.data.services.document

import com.app.daniel.ifdoc.commons.api.Constants
import com.app.daniel.ifdoc.data.entities.responses.BaseResponseEntity
import com.app.daniel.ifdoc.data.entities.responses.DocumentResponseEntity
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*


interface DocumentService {

    @GET(Constants.Api.API_URL + Constants.Endpoint.DOCUMENTS)
    fun myDocuments(): Single<DocumentResponseEntity>

    @Multipart
    @POST(Constants.Api.API_URL + Constants.Endpoint.DOCUMENTS)
    fun createDocument(@Part("description") description: RequestBody,
                       @Part file: MultipartBody.Part,
                       @Part("type") documentType: RequestBody): Single<DocumentResponseEntity>

    @GET(Constants.Api.API_URL + Constants.Endpoint.DOCUMENTS + "/{document_id}")
    fun getDocument(@Path("document_id") documentId: Int): Single<DocumentResponseEntity>

    @GET(Constants.Api.API_URL + Constants.Endpoint.DOCUMENTS + "/{document_id}")
    fun getDocumentDetails(@Path("document_id") documentId: Int): Single<ResponseBody>

    @DELETE( Constants.Api.API_URL + Constants.Endpoint.DOCUMENTS+"/{id}")
    fun deleteDocument(@Path("id") documentId: Int) : Single<BaseResponseEntity>

}