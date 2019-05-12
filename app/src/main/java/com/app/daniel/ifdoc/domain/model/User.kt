package com.app.daniel.ifdoc.domain.model

data class User(
        override var id: Int,
        override var created_at: String,
        override var updated_at: String,
        var name: String,
        var email: String
) : BaseModel