package com.app.daniel.ifdoc.data.repository.user

import com.app.daniel.ifdoc.data.entities.UserEntity
import com.app.daniel.ifdoc.domain.model.User

internal fun UserEntity.toUser() = User(
        id = this.id,
        name = this.name,
        created_at = this.created_at,
        updated_at = this.updated_at,
        email = this.email
)