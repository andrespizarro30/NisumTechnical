package com.afsoftwaresolutions.nisumtechnical.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afsoftwaresolutions.nisumtechnical.data.database.entities.MusicInfoEntity

@Dao
interface MusicInfoDao {

    @Query("SELECT * FROM [music_info_table] WHERE artistName LIKE '%' || :search || '%' OR trackName LIKE '%' || :search || '%' ORDER BY trackId ASC")
    suspend fun getMusicInfoData(search : String):List<MusicInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusicInfoData(musicInfoData:List<MusicInfoEntity>)

}