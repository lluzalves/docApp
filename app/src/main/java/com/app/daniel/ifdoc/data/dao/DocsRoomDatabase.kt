package com.app.daniel.ifdoc.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.daniel.ifdoc.data.entities.responses.DocumentEntity
import com.app.daniel.ifdoc.data.entities.UserEntity

@Database(entities = [UserEntity::class, DocumentEntity::class], version = 1)
abstract class DocsRoomDatabase : RoomDatabase() {

    abstract var documentsDao : DocumentDao
    abstract var userDao : UserDao

    companion object {
        var INSTANCE: DocsRoomDatabase? = null

        fun getDatabase(context: Context): DocsRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(DocsRoomDatabase::class) {
                    INSTANCE = Room
                            .databaseBuilder(context.applicationContext, DocsRoomDatabase::class.java, "docBD")
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