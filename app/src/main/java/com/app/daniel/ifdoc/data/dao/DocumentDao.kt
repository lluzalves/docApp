package com.app.daniel.ifdoc.data.dao

import androidx.room.*
import com.app.daniel.ifdoc.data.entities.responses.DocumentEntity

@Dao
interface DocumentDao {

    @Query("select * from " + DocumentEntity.NAME)
    fun allDocuments(): List<DocumentEntity>

    @Update
    fun updateDocument(documentEntity: DocumentEntity)

    @Insert
    fun insertDocument(documentEntity: DocumentEntity)

    @Delete
    fun deleteDocument(documentEntity: DocumentEntity)
}