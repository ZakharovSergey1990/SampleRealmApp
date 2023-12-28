package ru.rickmasters.demo.data.localdatasource.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


open class DoorEntity() : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var favorites: Boolean = false
    var name: String = ""
    var room: String? = null
    var snapshot: String? = null

    constructor(favorites: Boolean, id: Int, name: String, room: String?, snapshot: String?) : this() {
        this.favorites = favorites
        this.id = id
        this.room = room
        this.snapshot = snapshot
        this.name = name
    }
}
