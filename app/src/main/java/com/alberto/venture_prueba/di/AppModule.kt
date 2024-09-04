package com.alberto.venture_prueba.di

import android.content.Context
import android.content.SharedPreferences
import com.alberto.venture_prueba.core.data.remote.ApiService
import com.alberto.venture_prueba.auth.data.repository.UserRepositoryImpl
import com.alberto.venture_prueba.auth.domain.repository.UserRepository
import com.alberto.venture_prueba.home.data.repository.EmployeeRepositoryImpl
import com.alberto.venture_prueba.home.domain.repository.EmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://eland-dk.humaneland.net/HumaneTime/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        apiService: ApiService,
        sharedPreferences: SharedPreferences
    ): UserRepository {
        return UserRepositoryImpl(apiService, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideEmployeeRepository(
        apiService: ApiService,
        sharedPreferences: SharedPreferences
    ): EmployeeRepository {
        return EmployeeRepositoryImpl(apiService, sharedPreferences)
    }

}