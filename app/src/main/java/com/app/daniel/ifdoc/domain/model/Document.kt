package com.app.daniel.ifdoc.domain.model

import java.io.Serializable

data class Document(
        override var id: Int,
        override var created_at: String,
        override var updated_at: String,
        val description: String,
        val userId: Int,
        val notification: String,
        val isValidated: String,
        val type: String
) : Serializable, BaseModel {

    fun statusDescription(isValidated: String): String {
        return when (isValidated) {
            "1" -> "Validado"
            "0" -> "Em análise"
            else -> "Pendente"
        }
    }
}