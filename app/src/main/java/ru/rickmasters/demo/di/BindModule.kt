package ru.rickmasters.demo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.rickmasters.demo.data.localdatasource.CameraLocalDataSource
import ru.rickmasters.demo.data.localdatasource.CameraLocalDataSourceImpl
import ru.rickmasters.demo.data.localdatasource.DoorsLocalDataSource
import ru.rickmasters.demo.data.localdatasource.DoorsLocalDataSourceImpl
import ru.rickmasters.demo.data.remotedatasource.CamerasRemoteDataSource
import ru.rickmasters.demo.data.remotedatasource.CamerasRemoteDataSourceImpl
import ru.rickmasters.demo.data.remotedatasource.DoorsRemoteDataSource
import ru.rickmasters.demo.data.remotedatasource.DoorsRemoteDataSourceImpl
import ru.rickmasters.demo.data.repository.CamerasRepositoryImpl
import ru.rickmasters.demo.data.repository.DoorsRepositoryImpl
import ru.rickmasters.demo.domain.repository.CamerasRepository
import ru.rickmasters.demo.domain.repository.DoorsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindModule {

    @Singleton
    @Binds
    fun bindCamerasRepository(impl: CamerasRepositoryImpl): CamerasRepository

    @Singleton
    @Binds
    fun bindDoorsRepository(impl: DoorsRepositoryImpl): DoorsRepository

    @Singleton
    @Binds
    fun bindCamerasRemoteDataSource(impl: CamerasRemoteDataSourceImpl): CamerasRemoteDataSource

    @Singleton
    @Binds
    fun bindCameraLocalDataSource(impl: CameraLocalDataSourceImpl): CameraLocalDataSource

    @Singleton
    @Binds
    fun bindDoorsRemoteDataSource(impl: DoorsRemoteDataSourceImpl): DoorsRemoteDataSource

    @Singleton
    @Binds
    fun bindDoorsLocalDataSource(impl: DoorsLocalDataSourceImpl): DoorsLocalDataSource
}