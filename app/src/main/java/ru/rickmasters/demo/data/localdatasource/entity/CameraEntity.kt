package ru.rickmasters.demo.data.localdatasource.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class CameraEntity() : RealmObject {
    var favorites: Boolean = false
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var rec: Boolean = false
    var room: String? = null
    var snapshot: String? = null

    constructor(favorites: Boolean, id: Int, name: String, rec: Boolean, room: String?, snapshot: String?) : this() {
        this.favorites = favorites
        this.id = id
        this.rec = rec
        this.room = room
        this.snapshot = snapshot
        this.name = name
    }
}