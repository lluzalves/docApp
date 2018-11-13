package com.app.daniel.ifdoc.domain.model

import com.app.daniel.ifdoc.data.entities.CreatedAt
import com.app.daniel.ifdoc.data.entities.UpdateAt

data class User(
        override var id: Int,
        override var created_at: CreatedAt,
        override var updated_at: UpdateAt,
        val username: String,
        val token: String,
        val token_expiration_date: String,
        val email: String
) : BaseModel