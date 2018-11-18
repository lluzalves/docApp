package com.app.daniel.ifdoc.domain.model

import com.app.daniel.ifdoc.data.entities.CreatedAt
import com.app.daniel.ifdoc.data.entities.UpdateAt

data class Document(
        override var id: Int,
        override var created_at: CreatedAt,
        override var updated_at: UpdateAt,
        val description: String,
        val fileUrl: String,
        val userId: Int,
        val notification: String
) : BaseModel