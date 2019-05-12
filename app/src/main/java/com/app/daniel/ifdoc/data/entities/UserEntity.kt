package com.app.daniel.ifdoc.data.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = UserEntity.NAME)
data class UserEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        @SerializedName(Field.ID)
        override var id: Int,

        @ColumnInfo(name = "created_at")
        override var created_at: String,

        @ColumnInfo(name = "updated_at")
        override var updated_at: String,

         @ColumnInfo(name = "name")
        @SerializedName(Field.NAME)
        val name: String,

        @ColumnInfo(name = "user_email")
        @SerializedName(Field.EMAIL)
        val email: String
) : BaseEntity, JsonConvertable {

    companion object {
        const val NAME = "user"

        object Field {
            const val ID = "id"
            const val NAME = "name"
            const val EMAIL = "email"
            const val TOKEN = "token"
            const val PASSWORD = "password"
            const val TOKEN_EXPIRATION = "token_expiration"
        }
    }
}