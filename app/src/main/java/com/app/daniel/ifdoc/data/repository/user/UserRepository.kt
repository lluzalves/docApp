package com.app.daniel.ifdoc.data.repository.user

import android.app.Application
import com.app.daniel.ifdoc.data.dao.DocsRoomDatabase
import com.app.daniel.ifdoc.data.entities.UserEntity
import com.app.daniel.ifdoc.domain.model.User
import io.reactivex.Single

class UserRepository constructor(application: Application) {
    var database = DocsRoomDatabase.getDatabase(application.applicationContext)
    var dao = database?.userDao


    fun getUser(): Single<User> {
        return Single.fromCallable {
            dao?.getUser()?.toUser()
        }
    }


    fun insertUser(user: UserEntity): Single<Long> {
        return Single.fromCallable {
            dao?.insertUser(user)
        }
    }
}
