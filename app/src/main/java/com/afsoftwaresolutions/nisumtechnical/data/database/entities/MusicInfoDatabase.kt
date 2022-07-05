package com.afsoftwaresolutions.nisumtechnical.data.database.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import com.afsoftwaresolutions.nisumtechnical.data.database.dao.MusicInfoDao

@Database(entities = [MusicInfoEntity::class],version = 1)
abstract class MusicInfoDatabase : RoomDatabase() {

    abstract fun getMusicInfoDao():MusicInfoDao

}