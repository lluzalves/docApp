package com.app.daniel.ifdoc.data.entities.responses

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.daniel.ifdoc.data.entities.BaseEntity
import com.app.daniel.ifdoc.data.entities.JsonConvertable
import com.app.daniel.ifdoc.data.entities.toObject
import com.google.gson.annotations.SerializedName

@Entity(tableName = DocumentEntity.NAME)
data class DocumentEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        override var id: Int,

        @ColumnInfo(name = "created_at")
        @SerializedName(Field.CREATED_AT)
        override var created_at: String,

        @ColumnInfo(name = "updated_at")
        @SerializedName(Field.UPDATED_AT)
        override var updated_at: String,

        @ColumnInfo(name = "user_id")
        @SerializedName(Field.USER_ID)
        val userId: Int,

        @ColumnInfo(name = "description")
        @SerializedName(Field.DESCRIPTION)
        val description: String,

        @ColumnInfo(name = "file_url")
        @SerializedName(Field.FILE_URL)
        val fileUrl: String,

        @ColumnInfo(name = "notification")
        @SerializedName(Field.NOTIFICATION)
        val notification: String
) : BaseEntity, JsonConvertable {

    lateinit var json: String

    override fun toJson(): String {
        return super.toJson()
    }

    val document = json.toObject<DocumentEntity>()

    companion object {
        const val NAME = "documents"

        object Field {
            const val ID = "id"
            const val USER_ID = "user_id"
            const val DESCRIPTION = "description"
            const val FILE_URL = "file_url"
            const val CREATED_AT = "created_at"
            const val UPDATED_AT = "updated_at"
            const val NOTIFICATION = "notification"
        }
    }
}