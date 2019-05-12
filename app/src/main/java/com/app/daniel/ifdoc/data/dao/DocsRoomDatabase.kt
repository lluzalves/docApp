package com.app.daniel.ifdoc.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.daniel.ifdoc.data.entities.DocumentEntity
import com.app.daniel.ifdoc.data.entities.EdictEntity
import com.app.daniel.ifdoc.data.entities.UserEntity

@Database(entities = [UserEntity::class, DocumentEntity::class, EdictEntity::class], version = 1)
abstract class DocsRoomDatabase : RoomDatabase() {

    abstract val documentsDao : DocumentDao
    abstract val edictDao : EdictDao

    companion object {
        var INSTANCE: DocsRoomDatabase? = null

        fun getDatabase(context: Context): DocsRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(DocsRoomDatabase::class) {
                    INSTANCE = Room
                            .databaseBuilder(context, DocsRoomDatabase::class.java, "docBD")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}