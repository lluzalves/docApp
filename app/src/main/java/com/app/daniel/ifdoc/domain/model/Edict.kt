package com.app.daniel.ifdoc.domain.model

import java.io.Serializable

data class Edict(
        override var id: Int,
        override var created_at: String,
        override var updated_at: String,
        var title: String,
        var starts_at: String,
        var description: String,
        var type: String,
        var end_at: String,
        var roles: String,
        var notification: String
) : BaseModel, Serializable