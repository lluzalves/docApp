package com.app.daniel.ifdoc.data.dao

import androidx.room.*
import com.app.daniel.ifdoc.data.entities.DocumentEntity

@Dao
interface DocumentDao {

    @Query("select * from " + DocumentEntity.NAME)
    fun allDocuments(): List<DocumentEntity>

    @Update
    fun updateDocument(documentEntity: DocumentEntity)

    @Insert
    fun insertDocument(documentEntity: DocumentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDocuments(documentEntity: List<DocumentEntity>) : Array<Long>

    @Query("DELETE FROM documents WHERE id = :documentId")
    fun deleteDocument(documentId: Int)
}