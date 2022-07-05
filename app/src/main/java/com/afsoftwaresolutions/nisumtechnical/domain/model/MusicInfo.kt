package com.afsoftwaresolutions.nisumtechnical.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.afsoftwaresolutions.nisumtechnical.data.database.entities.MusicInfoEntity
import com.afsoftwaresolutions.nisumtechnical.data.model.MusicInfoModel

data class MusicInfo (
        val trackId : Int,
        val artistName : String,
        val trackName : String,
        val trackCensoredName : String,
        val trackViewUrl : String,
        val previewUrl : String,
        val artworkUrl30 : String,
        val artworkUrl60 : String,
        val artworkUrl100 : String
)

fun MusicInfoModel.toDomain() = MusicInfo(trackId,artistName,trackName, trackCensoredName, trackViewUrl, previewUrl, artworkUrl30, artworkUrl60, artworkUrl100)
fun MusicInfoEntity.toDomain() = MusicInfo(trackId, artistName, trackName, trackCensoredName, trackViewUrl, previewUrl, artworkUrl30, artworkUrl60, artworkUrl100)
