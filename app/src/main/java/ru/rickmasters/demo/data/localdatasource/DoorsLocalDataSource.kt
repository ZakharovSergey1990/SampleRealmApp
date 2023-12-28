package ru.rickmasters.demo.data.localdatasource

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import ru.rickmasters.demo.data.localdatasource.entity.CameraEntity
import ru.rickmasters.demo.data.localdatasource.entity.DoorEntity
import ru.rickmasters.demo.data.remotedatasource.dto.DoorsRequestResult
import ru.rickmasters.demo.domain.model.Door
import ru.rickmasters.demo.domain.model.Resource
import javax.inject.Inject

interface DoorsLocalDataSource {
    suspend fun getDoors(): RealmResults<DoorEntity>
    suspend fun insertDoors(doors: List<DoorEntity>)
    suspend fun clear()
}

class DoorsLocalDataSourceImpl @Inject constructor(
    private val realm: Realm
): DoorsLocalDataSource{
    override suspend fun getDoors(): RealmResults<DoorEntity> {
        return realm.query<DoorEntity>().find()
    }

    override suspend fun insertDoors(doors: List<DoorEntity>) {
        realm.write {
            doors.forEach {
                copyToRealm(it)
            }
        }
    }

    override suspend fun clear() {
        realm.write {
            this.delete(DoorEntity::class)
        }
    }
}
