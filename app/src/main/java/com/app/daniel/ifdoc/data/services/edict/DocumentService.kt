package com.app.daniel.ifdoc.data.services.edict

import com.app.daniel.ifdoc.commons.api.Constants
import com.app.daniel.ifdoc.data.entities.responses.EdictResponseEntity
import io.reactivex.Single
import retrofit2.http.*


interface EdictService {

    @GET(Constants.Api.API_URL + Constants.Endpoint.EDICT + "/user/all")
    fun availableEdicts(): Single<EdictResponseEntity>
}