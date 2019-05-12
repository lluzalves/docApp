package com.app.daniel.ifdoc.data.repository.documents

import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.domain.model.Document

internal fun DocumentEntity.toDocument() = Document(
        id = this.id,
        userId = this.userId,
        created_at = this.created_at,
        updated_at = this.updated_at,
        description = this.description,
        notification = this.notification,
        type = this.type,
        isValidated = this.isValidated
)