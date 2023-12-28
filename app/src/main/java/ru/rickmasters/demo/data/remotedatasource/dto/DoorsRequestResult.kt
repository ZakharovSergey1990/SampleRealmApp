package ru.rickmasters.demo.data.remotedatasource.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


class DoorsRequestResult(
    @SerializedName("data") val data: List<DoorDto>,
    @SerializedName("success") val success: Boolean
)


data class DoorDto(
    @SerializedName("favorites") val favorites: Boolean,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("room") val room: String? = null,
    @SerializedName("snapshot") val snapshot: String?
)