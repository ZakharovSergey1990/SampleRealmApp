package ru.rickmasters.demo.presentation.page.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.rickmasters.demo.domain.model.CamerasState
import ru.rickmasters.demo.domain.model.DoorsState
import ru.rickmasters.demo.domain.model.HomeEvent
import ru.rickmasters.demo.domain.model.HomeState
import ru.rickmasters.demo.domain.model.ViewState
import ru.rickmasters.demo.domain.usecase.GetHomeStateUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeStateUseCase: GetHomeStateUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeState(doorsState = DoorsState(ViewState.Loading), camerasState = CamerasState(ViewState.Loading)))
    val state: StateFlow<HomeState> = _state

    init {
        onEvent(HomeEvent.GetCameras)
    }

    fun onEvent(event: HomeEvent){
        Log.i("HomeViewModel", "onEvent : $event")
           getHomeStateUseCase.execute(state.value, event).onEach {
               Log.i("HomeViewModel", "state = $it")
               _state.value = it
           }.launchIn(viewModelScope)
    }
}