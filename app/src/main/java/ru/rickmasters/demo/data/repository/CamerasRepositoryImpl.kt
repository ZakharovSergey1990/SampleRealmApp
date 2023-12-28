package ru.rickmasters.demo.data.repository

import android.util.Log
import ru.rickmasters.demo.data.localdatasource.CameraLocalDataSource
import ru.rickmasters.demo.data.localdatasource.entity.CameraEntity
import ru.rickmasters.demo.data.remotedatasource.CamerasRemoteDataSource
import ru.rickmasters.demo.data.remotedatasource.dto.CameraDto
import ru.rickmasters.demo.data.remotedatasource.dto.DoorDto
import ru.rickmasters.demo.domain.model.Camera
import ru.rickmasters.demo.domain.model.Door
import ru.rickmasters.demo.domain.model.Resource
import ru.rickmasters.demo.domain.repository.CamerasRepository
import javax.inject.Inject


class CamerasRepositoryImpl @Inject constructor(
    private val camerasRemoteDataSource: CamerasRemoteDataSource,
    private val cameraLocalDataSource: CameraLocalDataSource
): CamerasRepository {
    override suspend fun getCameras(): Resource<List<Camera>> {
        val localCameras = cameraLocalDataSource.getCameras().toList()
        Log.i("CamerasRepositoryImpl", "local = $localCameras")
        if(localCameras.isNotEmpty()) return Resource.Success(localCameras.map { it.toCamera() })

        val result = camerasRemoteDataSource.getCameras()
        Log.i("CamerasRepositoryImpl", "remote = $result")
        if( result is Resource.Error) return Resource.Error(result.message)

        val cameras = (result as Resource.Success).data.data.cameras

        cameraLocalDataSource.insertCameras(cameras.map { it.toCameraEntity() })

        return Resource.Success(cameras.map{ it.toCamera() })
    }

    override suspend fun refresh(): Resource<List<Camera>> {
        val result = camerasRemoteDataSource.getCameras()
        Log.i("CamerasRepositoryImpl", "remote = $result")
        if(result is Resource.Error) {
            val localCameras = cameraLocalDataSource.getCameras().toList()
            return Resource.Success(localCameras.map { it.toCamera() })
        }

        val cameras = (result as Resource.Success).data.data.cameras

        cameraLocalDataSource.clear()

        return Resource.Success(cameras.map{ it.toCamera() })
    }

    private fun CameraDto.toCamera() = Camera(favorites, id, name, rec, room, snapshot)

    private fun CameraEntity.toCamera() = Camera(favorites, id, name, rec, room, snapshot)

    private fun CameraDto.toCameraEntity() = CameraEntity(favorites, id, name, rec, room, snapshot)
}