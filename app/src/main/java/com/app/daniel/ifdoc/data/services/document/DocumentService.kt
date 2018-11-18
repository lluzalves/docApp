package com.app.daniel.ifdoc.data.services.document

import retrofit2.http.GET
import com.app.daniel.ifdoc.commons.Constants
import com.app.daniel.ifdoc.data.entities.DocumentResponseEntity
import io.reactivex.Single


interface DocumentService {

    @GET(Constants.Api.API_URL + Constants.Endpoint.DOCUMENTS )
    fun myDocuments(): Single<DocumentResponseEntity>

}