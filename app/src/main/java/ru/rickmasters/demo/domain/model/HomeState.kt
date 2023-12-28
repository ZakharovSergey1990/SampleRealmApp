package ru.rickmasters.demo.domain.model

import androidx.compose.runtime.Stable

@Stable
data class HomeState(
    val tabPosition: Int = 0,
    val doorsState: DoorsState,
    val camerasState: CamerasState
)
@Stable
data class DoorsState(
    val doors: ViewState<List<Door>>,
    val isRefreshing: Boolean = false
)
@Stable
data class CamerasState(
    val rooms: ViewState<List<Camera>>,
    val isRefreshing: Boolean = false
)
