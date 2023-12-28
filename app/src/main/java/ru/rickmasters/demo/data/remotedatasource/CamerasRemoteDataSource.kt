package ru.rickmasters.demo.data.remotedatasource

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import ru.rickmasters.demo.data.remotedatasource.api.ApiRoutes
import ru.rickmasters.demo.data.remotedatasource.dto.CameraRequestResult
import ru.rickmasters.demo.data.remotedatasource.dto.DoorsRequestResult
import ru.rickmasters.demo.domain.model.Camera
import ru.rickmasters.demo.domain.model.Resource
import java.util.concurrent.CancellationException
import javax.inject.Inject

interface CamerasRemoteDataSource {
    suspend fun getCameras(): Resource<CameraRequestResult>
}

class CamerasRemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : CamerasRemoteDataSource {
    override suspend fun getCameras(): Resource<CameraRequestResult> {
       try {
            val response: HttpResponse = httpClient.get(ApiRoutes.CAMERAS)
            val result: CameraRequestResult = response.body()
           Log.i("CamerasRemoteDataSourceImpl", "getCameras: $result")
            return Resource.Success(result)
        } catch (e: Exception) {
            if(e is CancellationException) throw e
            return Resource.Error(e.message)
        }
    }
}