package com.app.daniel.ifdoc.data.entities

data class DocumentResponseEntity (
    var message: String,
    var code: Int,
    var documents: List<DocumentEntity>
)


