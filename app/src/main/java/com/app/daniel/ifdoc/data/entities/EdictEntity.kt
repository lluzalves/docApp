package com.app.daniel.ifdoc.data.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = EdictEntity.NAME)
data class EdictEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        @SerializedName(Field.ID)
        override var id: Int,

        @ColumnInfo(name = "created_at")
        @SerializedName(Field.CREATED_AT)
        override var created_at: String,

        @ColumnInfo(name = "updated_at")
        @SerializedName(Field.UPDATED_AT)
        override var updated_at: String,

        @ColumnInfo(name = "roles")
        @SerializedName(Field.ROLES)
        val roles: String,

        @ColumnInfo(name = "title")
        @SerializedName(Field.TITLE)
        val title: String,

        @ColumnInfo(name = "description")
        @SerializedName(Field.DESCRIPTION)
        val description: String,

        @ColumnInfo(name = "type")
        @SerializedName(Field.TYPE)
        val type: String,

        @ColumnInfo(name = "is_available")
        @SerializedName(Field.IS_AVAILABLE)
        val isAvailable: String,

        @ColumnInfo(name = "starts_at")
        @SerializedName(Field.STARTS_AT)
        val startsAt: String,

        @ColumnInfo(name = "end_at")
        @SerializedName(Field.ENDS_AT)
        val endAt: String,

        @ColumnInfo(name = "notification")
        @SerializedName(Field.NOTIFICATION)
        val notification: String
) : BaseEntity {

    companion object {
        const val NAME = "edict"

        object Field {
            const val ID = "id"
            const val ROLES = "roles"
            const val DESCRIPTION = "description"
            const val TITLE = "title"
            const val CREATED_AT = "created_at"
            const val UPDATED_AT = "updated_at"
            const val STARTS_AT = "starts_at"
            const val ENDS_AT = "end_at"
            const val NOTIFICATION = "notification"
            const val IS_AVAILABLE = "isAvailable"
            const val TYPE = "type"
        }
    }
}