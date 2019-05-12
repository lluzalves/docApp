package com.app.daniel.ifdoc.data.entities.responses

import com.app.daniel.ifdoc.data.entities.UserEntity

data class UserResponseEntity (
    var message: String,
    var code: String,
    var user: UserEntity
)


