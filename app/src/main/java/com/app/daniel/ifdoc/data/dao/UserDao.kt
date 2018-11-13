package com.app.daniel.ifdoc.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.app.daniel.ifdoc.data.entities.UserEntity

@Dao
interface UserDao {

    @Query("select * from "+UserEntity.NAME)
    fun findUser() : List<UserEntity>

    @Update
    fun updateUser(userEntity: UserEntity)

}