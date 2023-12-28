package ru.rickmasters.demo.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}