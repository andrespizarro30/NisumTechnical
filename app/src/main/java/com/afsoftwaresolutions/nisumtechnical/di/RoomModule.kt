package com.afsoftwaresolutions.nisumtechnical.di

import android.content.Context
import androidx.room.Room
import com.afsoftwaresolutions.nisumtechnical.data.database.entities.MusicInfoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private  val DATA_BASE_MUSIC_INFO = "MusicInfoDatabase"

    @Singleton
    @Provides
    fun provideMusicInfoDBRoom(@ApplicationContext context: Context) =
            Room.databaseBuilder(context, MusicInfoDatabase::class.java, DATA_BASE_MUSIC_INFO).build()

    @Singleton
    @Provides
    fun provideMusicInfoDao(db: MusicInfoDatabase) = db.getMusicInfoDao()


}