package com.app.daniel.ifdoc.data.services

import retrofit2.http.GET
import com.app.daniel.ifdoc.commons.Constants
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.data.entities.ResponseEntity
import io.reactivex.Single
import retrofit2.http.Header
import retrofit2.http.Query


interface DocumentService {

    @GET(Constants.Api.API_URL + Constants.Endpoint.DOCUMENTS )
    fun myDocuments(): Single<ResponseEntity>

}