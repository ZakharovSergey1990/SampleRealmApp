package ru.rickmasters.demo.domain.model

sealed class ViewState <out T> {
    object Loading : ViewState <Nothing> ()
    data class Success <T> ( val result: T ): ViewState<T>()
    data class Error(val errorMessage: String?): ViewState <Nothing>()
}