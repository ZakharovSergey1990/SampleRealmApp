package ru.rickmasters.demo.domain.repository

import ru.rickmasters.demo.domain.model.Camera
import ru.rickmasters.demo.domain.model.Door
import ru.rickmasters.demo.domain.model.Resource

interface DoorsRepository {
    suspend fun getDoors(): Resource<List<Door>>
    suspend fun refresh(): Resource<List<Door>>
}