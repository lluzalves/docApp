package com.app.daniel.ifdoc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.daniel.ifdoc.data.entities.EdictEntity

@Dao
interface EdictDao {

    @Query("select * from " + EdictEntity.NAME)
    fun allEdict(): List<EdictEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEdict(edictEntity: List<EdictEntity>): Array<Long>
}