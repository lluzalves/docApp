package com.app.daniel.ifdoc.data.entities.responses

import com.app.daniel.ifdoc.data.entities.EdictEntity

data class EdictResponseEntity (
    var message: String,
    var code: Int,
    var documents: List<EdictEntity>
)


