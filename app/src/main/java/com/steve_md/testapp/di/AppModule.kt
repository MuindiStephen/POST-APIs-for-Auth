package com.steve_md.testapp.di

import com.steve_md.testapp.data.remote.UserApiService
import com.steve_md.testapp.data.repositories.AuthUserRepository
import com.steve_md.testapp.data.repositories.AuthUserRepositoryImpl
import com.steve_md.testapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Hilt for Repository
    @Singleton
    @Provides
    fun providesUserAuthRepository(userApiService: UserApiService) : AuthUserRepositoryImpl{
         return AuthUserRepositoryImpl()
    }


    // Hilt for User Api Service
    @Singleton
    @Provides
    fun providesUserApiService()  {

    }

// Hilt for Base Url of the auth apis
    @Singleton
    @Provides
    fun providesBaseUrl() : String {
        return BASE_URL
    }
}