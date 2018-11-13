package com.app.daniel.ifdoc.data.entities

import androidx.room.ColumnInfo

interface BaseEntity {
    var id: Int
    var created_at: CreatedAt
    var updated_at: UpdateAt
}