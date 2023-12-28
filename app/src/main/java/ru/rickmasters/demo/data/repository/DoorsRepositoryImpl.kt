package ru.rickmasters.demo.data.repository

import android.util.Log
import ru.rickmasters.demo.data.localdatasource.DoorsLocalDataSource
import ru.rickmasters.demo.data.localdatasource.entity.DoorEntity
import ru.rickmasters.demo.data.remotedatasource.DoorsRemoteDataSource
import ru.rickmasters.demo.data.remotedatasource.dto.DoorDto
import ru.rickmasters.demo.domain.model.Door
import ru.rickmasters.demo.domain.model.Resource
import ru.rickmasters.demo.domain.repository.DoorsRepository
import javax.inject.Inject

class DoorsRepositoryImpl @Inject constructor(
    private val doorsRemoteDataSource: DoorsRemoteDataSource,
    private val doorsLocalDataSource: DoorsLocalDataSource
): DoorsRepository{
    
    override suspend fun getDoors(): Resource<List<Door>> {

        val localDoors = doorsLocalDataSource.getDoors().toList()
        Log.i("CamerasRepositoryImpl", "local = $localDoors")
        if(localDoors.isNotEmpty()) return Resource.Success(localDoors.map { it.toDoor() })

        val result = doorsRemoteDataSource.getDoors()
        Log.i("CamerasRepositoryImpl", "remote = $result")
        if( result is Resource.Error) return Resource.Error(result.message)

        val cameras = (result as Resource.Success).data.data

        doorsLocalDataSource.insertDoors(cameras.map { it.toDoorEntity() })

        return Resource.Success(cameras.map{ it.toDoor() })
    }

    override suspend fun refresh(): Resource<List<Door>> {
        val result = doorsRemoteDataSource.getDoors()
        Log.i("CamerasRepositoryImpl", "remote = $result")
        if(result is Resource.Error) {
            val localDoors = doorsLocalDataSource.getDoors().toList()
            return Resource.Success(localDoors.map { it.toDoor() })
        }

        val doors = (result as Resource.Success).data.data

        doorsLocalDataSource.clear()

        return Resource.Success(doors.map{ it.toDoor() })
    }

    private fun DoorDto.toDoor() = Door(favorites, id, name, room, snapshot)

    private fun DoorDto.toDoorEntity() = DoorEntity(favorites, id, name, room, snapshot)

    private fun DoorEntity.toDoor() = Door(favorites, id, name, room, snapshot)
}