package com.app.daniel.ifdoc.data.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = DocumentEntity.NAME)
data class DocumentEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        override var id: Int,

        @ColumnInfo(name = "created_at")
        override var created_at: CreatedAt,

        @ColumnInfo(name = "updated_at")
        override var updated_at: UpdateAt,

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