package ru.rickmasters.demo.data.remotedatasource

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import ru.rickmasters.demo.data.remotedatasource.api.ApiRoutes
import ru.rickmasters.demo.data.remotedatasource.dto.CameraRequestResult
import ru.rickmasters.demo.data.remotedatasource.dto.DoorsRequestResult
import ru.rickmasters.demo.domain.model.Resource
import java.util.concurrent.CancellationException
import javax.inject.Inject

interface DoorsRemoteDataSource {
    suspend fun getDoors(): Resource<DoorsRequestResult>
}

class DoorsRemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : DoorsRemoteDataSource {
    override suspend fun getDoors(): Resource<DoorsRequestResult> {
        try {
            val response: HttpResponse = httpClient.get(ApiRoutes.DOORS)
            Log.i("DoorsRemoteDataSourceImpl", "response = $response")
            val result: DoorsRequestResult = response.body<DoorsRequestResult>()
            return Resource.Success(result)
        } catch (e: Exception) {
            if(e is CancellationException) throw e
            return Resource.Error(e.message)
        }
    }
}