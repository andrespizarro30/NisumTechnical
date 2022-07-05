package com.afsoftwaresolutions.nisumtechnical.di

import com.afsoftwaresolutions.nisumtechnical.data.network.InfoMusicApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val baseUrl="https://itunes.apple.com"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideInfoMusicApiClient(retrofit: Retrofit):InfoMusicApiClient{
        return retrofit.create(InfoMusicApiClient::class.java)
    }

}