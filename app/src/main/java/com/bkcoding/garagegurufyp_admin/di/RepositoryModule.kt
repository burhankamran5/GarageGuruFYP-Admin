package com.bkcoding.garagegurufyp_admin.di

import com.bkcoding.garagegurufyp_admin.repository.AdminRepository
import com.bkcoding.garagegurufyp_admin.repository.AdminRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providesFirebaseAuthRepository(repo: AdminRepositoryImpl): AdminRepository
}