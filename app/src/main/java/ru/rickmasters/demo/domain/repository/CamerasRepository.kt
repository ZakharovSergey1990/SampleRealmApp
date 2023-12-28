package ru.rickmasters.demo.domain.repository

import ru.rickmasters.demo.domain.model.Camera
import ru.rickmasters.demo.domain.model.Door
import ru.rickmasters.demo.domain.model.Resource

interface CamerasRepository {
    suspend fun getCameras(): Resource<List<Camera>>
    suspend fun refresh(): Resource<List<Camera>>
}