package com.app.daniel.ifdoc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.daniel.ifdoc.data.entities.UserEntity

@Dao
interface UserDao {

    @Query("select * from " + UserEntity.NAME)
    fun getUser(): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity): Long
}