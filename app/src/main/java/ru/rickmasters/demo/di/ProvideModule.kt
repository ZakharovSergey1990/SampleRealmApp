package ru.rickmasters.demo.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.gson.gson
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.serialization.json.Json
import ru.rickmasters.demo.data.localdatasource.entity.CameraEntity
import ru.rickmasters.demo.data.localdatasource.entity.DoorEntity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
//            install(HttpTimeout) {
//                requestTimeoutMillis = 15000L
//                connectTimeoutMillis = 15000L
//                socketTimeoutMillis = 15000L
//            }
            install(ContentNegotiation) {
                gson(){
                    serializeNulls()
                }
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }

    @Provides
    @Singleton
    fun provideRealm(
        @ApplicationContext context: Context
    ): Realm {
        Realm
        val config = RealmConfiguration.Builder(schema = setOf(CameraEntity::class, DoorEntity::class)).compactOnLaunch().build()
        return Realm.open(config)
    }
}