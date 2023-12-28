package ru.rickmasters.demo.data.remotedatasource.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class CameraRequestResult(
    @SerializedName("data") val data: Data,
    @SerializedName("success")val success: Boolean
)


data class Data(
    @SerializedName("cameras") val cameras: List<CameraDto>,
    @SerializedName("room") val room: List<String>
)


data class CameraDto(
    @SerializedName("favorites")  val favorites: Boolean,
    @SerializedName("id")  val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rec") val rec: Boolean,
    @SerializedName("room") val room: String?,
    @SerializedName("snapshot") val snapshot: String
)