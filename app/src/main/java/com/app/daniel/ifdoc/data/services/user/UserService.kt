package com.app.daniel.ifdoc.data.services.user

import com.app.daniel.ifdoc.commons.Constants
import com.app.daniel.ifdoc.data.entities.ResponseEntity
import com.app.daniel.ifdoc.data.entities.UserResponseEntity
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @FormUrlEncoded
    @POST(Constants.Api.API_URL + Constants.Endpoint.REGISTER)
    fun createUser(@Field("name") name: String,
                   @Field("email") email: String,
                   @Field("password") password: String): Single<UserResponseEntity>

    @GET(Constants.Api.API_URL)
    fun login() : Single<ResponseEntity>
}