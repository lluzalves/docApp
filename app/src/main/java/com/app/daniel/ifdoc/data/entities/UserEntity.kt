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
        override var id: Int,

        @ColumnInfo(name = "created_at")
        override var created_at: String,

        @ColumnInfo(name = "updated_at")
        override var updated_at: String,

        @ColumnInfo(name = "username")
        @SerializedName(Field.USERNAME)
        val username: String,

        @ColumnInfo(name = "user_email")
        @SerializedName(Field.EMAIL)
        val email: String,

        @ColumnInfo(name = "user_token")
        @SerializedName(Field.TOKEN)
        val token: String,

        @ColumnInfo(name = "toke_expiration_date")
        @SerializedName(Field.TOKEN_EXPIRATION)
        val tokenExpirationDate: String
) : BaseEntity, JsonConvertable {

    lateinit var json: String

    override fun toJson(): String {
        return super.toJson()
    }

    val user = json.toObject<UserEntity>()

    companion object {
        const val NAME = "users"

        object Field {
            const val ID = "id"
            const val USERNAME = "username"
            const val EMAIL = "email"
            const val TOKEN = "token"
            const val TOKEN_EXPIRATION = "token_expiration"
        }
    }
}