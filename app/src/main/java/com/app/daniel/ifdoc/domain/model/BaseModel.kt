package com.app.daniel.ifdoc.domain.model

import com.app.daniel.ifdoc.data.entities.CreatedAt
import com.app.daniel.ifdoc.data.entities.UpdateAt

interface BaseModel {
    var id: Int
    var created_at: String
    var updated_at: String
}