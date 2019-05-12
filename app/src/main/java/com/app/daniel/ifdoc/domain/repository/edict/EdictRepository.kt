package com.app.daniel.ifdoc.domain.repository.edict

import android.app.Application
import com.app.daniel.ifdoc.data.dao.DocsRoomDatabase
import com.app.daniel.ifdoc.data.entities.EdictEntity
import com.app.daniel.ifdoc.domain.model.Edict
import io.reactivex.Single

class EdictRepository constructor(application: Application) {
    var database = DocsRoomDatabase.getDatabase(application.applicationContext)
    var dao = database?.edictDao


    fun allEdicts(): Single<List<Edict>> {
        return Single.fromCallable {
            dao?.allEdict()?.map {
                it.toEdict()
            }
        }
    }


    fun insertEdicts(edict: List<EdictEntity>): Single<Array<Long>> {
        return Single.fromCallable {
            dao?.insertEdict(edict)
        }
    }
}
