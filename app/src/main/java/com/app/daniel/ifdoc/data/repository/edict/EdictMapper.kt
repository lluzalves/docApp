package com.app.daniel.ifdoc.data.repository.edict

import com.app.daniel.ifdoc.data.entities.EdictEntity
import com.app.daniel.ifdoc.domain.model.Edict

internal fun EdictEntity.toEdict() = Edict(
        id = this.id,
        title = this.title,
        created_at = this.created_at,
        updated_at = this.updated_at,
        starts_at = this.startsAt,
        end_at = this.endAt,
        description = this.description,
        notification = this.notification,
        type = this.type,
        roles = this.roles
)