package ru.rickmasters.demo.domain.model

sealed class HomeEvent {
    data object GetCameras: HomeEvent()
    data object GetDoors: HomeEvent()
    data object RefreshCameras: HomeEvent()
    data object RefreshDoors: HomeEvent()
    data class SetTab(val tab: Int): HomeEvent()
}