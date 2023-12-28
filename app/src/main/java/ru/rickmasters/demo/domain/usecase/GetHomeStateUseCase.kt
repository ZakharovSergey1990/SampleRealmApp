package ru.rickmasters.demo.domain.usecase

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ru.rickmasters.demo.domain.model.Camera
import ru.rickmasters.demo.domain.model.CamerasState
import ru.rickmasters.demo.domain.model.Door
import ru.rickmasters.demo.domain.model.DoorsState
import ru.rickmasters.demo.domain.model.HomeEvent
import ru.rickmasters.demo.domain.model.HomeState
import ru.rickmasters.demo.domain.model.Resource
import ru.rickmasters.demo.domain.model.ViewState
import ru.rickmasters.demo.domain.repository.CamerasRepository
import ru.rickmasters.demo.domain.repository.DoorsRepository
import javax.inject.Inject

class GetHomeStateUseCase @Inject constructor(
    private val doorsRepository: DoorsRepository,
    private val camerasRepository: CamerasRepository
) {
    fun execute(state: HomeState, event: HomeEvent): Flow<HomeState> {
        return flow {
            when (event) {
                is HomeEvent.GetCameras -> {
                    val result: Resource<List<Camera>> = camerasRepository.getCameras()
                    Log.i("GetHomeStateUseCase", "result = $result")
                    if(result is Resource.Success) emit(state.copy(camerasState = CamerasState(rooms = ViewState.Success(result.data))))
                    else emit(state.copy(camerasState = CamerasState(rooms = ViewState.Error("Network error"))))
                }
                is HomeEvent.GetDoors -> {
                    val result: Resource<List<Door>> = doorsRepository.getDoors()
                    Log.i("GetHomeStateUseCase", "result = $result")
                    if(result is Resource.Success) emit(state.copy(doorsState = DoorsState(doors = ViewState.Success(result.data))))
                    else emit(state.copy(doorsState = DoorsState(ViewState.Error("network error"))))
                }
                is HomeEvent.RefreshCameras -> {
                    emit(state.copy(camerasState = state.camerasState.copy(isRefreshing = true)))
                    val result: Resource<List<Camera>> = camerasRepository.refresh()
                    if(result is Resource.Success) emit(state.copy(camerasState = CamerasState(isRefreshing = false, rooms = ViewState.Success(result.data))))
                }
                is HomeEvent.RefreshDoors -> {
                    emit(state.copy(doorsState = state.doorsState.copy(isRefreshing = true)))
                    val result: Resource<List<Door>> = doorsRepository.refresh()
                    if(result is Resource.Success) emit(state.copy(doorsState = DoorsState(isRefreshing = false,  doors = ViewState.Success( result.data))))
                }
                is HomeEvent.SetTab -> {
                    when(event.tab){
                        0 -> {
                            if(state.camerasState.rooms is ViewState.Success){
                                emit(state.copy(tabPosition = event.tab))
                            }
                            else{
                                val result: Resource<List<Camera>> = camerasRepository.getCameras()
                                Log.i("GetHomeStateUseCase", "result = $result")
                                if(result is Resource.Success) emit(state.copy(tabPosition = event.tab, camerasState = CamerasState(rooms = ViewState.Success(result.data))))
                            }
                        }
                        1 -> {
                            if(state.doorsState.doors is ViewState.Success){
                                emit(state.copy(tabPosition = event.tab))
                            }
                            else{
                                val result: Resource<List<Door>> = doorsRepository.getDoors()
                                Log.i("GetHomeStateUseCase", "result = $result")
                                if(result is Resource.Success) emit(state.copy(tabPosition = event.tab, doorsState = DoorsState(doors = ViewState.Success(result.data))))
                            }
                        }
                    }
                }
            }
        }
    }
}