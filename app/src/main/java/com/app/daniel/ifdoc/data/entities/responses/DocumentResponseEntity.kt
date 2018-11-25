package com.app.daniel.ifdoc.data.entities.responses

import com.app.daniel.ifdoc.data.entities.DocumentEntity

data class DocumentResponseEntity (
    var message: String,
    var code: Int,
    var documents: List<DocumentEntity>
)


