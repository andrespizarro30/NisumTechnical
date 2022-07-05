package com.afsoftwaresolutions.nisumtechnical.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afsoftwaresolutions.nisumtechnical.domain.model.MusicInfo
import com.google.gson.annotations.SerializedName

@Entity(tableName = "music_info_table")
data class MusicInfoEntity(
        @PrimaryKey(autoGenerate = false) val trackId : Int,
        @ColumnInfo(name="artistName") val artistName : String,
        @ColumnInfo(name="trackName") val trackName : String,
        @ColumnInfo(name="trackCensoredName") val trackCensoredName : String,
        @ColumnInfo(name="trackViewUrl") val trackViewUrl : String,
        @ColumnInfo(name="previewUrl") val previewUrl : String,
        @ColumnInfo(name="artworkUrl30") val artworkUrl30 : String,
        @ColumnInfo(name="artworkUrl60") val artworkUrl60 : String,
        @ColumnInfo(name="artworkUrl100") val artworkUrl100 : String
)

fun MusicInfo.toDatabase() = MusicInfoEntity(trackId = trackId,artistName = artistName,trackCensoredName=trackCensoredName,trackName = trackName,trackViewUrl = trackViewUrl,previewUrl = previewUrl,artworkUrl30 = artworkUrl30,artworkUrl60 = artworkUrl60,artworkUrl100 = artworkUrl100)

