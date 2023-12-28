package ru.rickmasters.demo.domain.model

sealed class Resource <out T> {
    data class Success<T>(val data: T): Resource<T>()
    data class Error<T>(val message: String?, val data: T? = null): Resource<T>()
}