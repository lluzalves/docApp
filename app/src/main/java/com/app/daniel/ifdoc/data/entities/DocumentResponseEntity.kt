package com.app.daniel.ifdoc.data.entities

import com.app.daniel.ifdoc.data.entities.responses.DocumentEntity

data class DocumentResponseEntity (
    var message: String,
    var code: Int,
    var documents: List<DocumentEntity>
)


