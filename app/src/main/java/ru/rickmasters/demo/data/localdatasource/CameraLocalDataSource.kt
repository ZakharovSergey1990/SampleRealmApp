package ru.rickmasters.demo.data.localdatasource

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import ru.rickmasters.demo.data.localdatasource.entity.CameraEntity
import javax.inject.Inject

interface CameraLocalDataSource {
    suspend fun getCameras(): RealmResults<CameraEntity>
    suspend fun insertCameras(cameras: List<CameraEntity>)
    suspend fun clear()
}


class CameraLocalDataSourceImpl @Inject constructor(
    private val realm: Realm
): CameraLocalDataSource {
    override suspend fun getCameras(): RealmResults<CameraEntity> {
        return realm.query<CameraEntity>().find()
    }

    override suspend fun insertCameras(cameras: List<CameraEntity>){
        realm.write {
            cameras.forEach {
                copyToRealm(it)
            }
        }
    }

    override suspend fun clear() {
        realm.write {
            this.delete(CameraEntity::class)
        }
    }
}