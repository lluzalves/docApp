package com.app.daniel.ifdoc.domain.model

data class User(
        override var id: Int,
        override var created_at: String,
        override var updated_at: String,
        val username: String,
        val token: String,
        val token_expiration_date: String,
        val email: String
) : BaseModel