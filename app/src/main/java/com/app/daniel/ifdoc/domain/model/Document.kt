package com.app.daniel.ifdoc.domain.model

data class Document(
        override var id: Int,
        override var created_at: String,
        override var updated_at: String,
        val description: String,
        val fileUrl: String,
        val userId: Int
) : BaseModel