package com.afsoftwaresolutions.nisumtechnical.data

import com.afsoftwaresolutions.nisumtechnical.data.database.dao.MusicInfoDao
import com.afsoftwaresolutions.nisumtechnical.data.database.entities.MusicInfoEntity
import com.afsoftwaresolutions.nisumtechnical.data.model.ResultsModel
import com.afsoftwaresolutions.nisumtechnical.data.network.InfoMusicService
import com.afsoftwaresolutions.nisumtechnical.domain.model.MusicInfo
import com.afsoftwaresolutions.nisumtechnical.domain.model.toDomain
import javax.inject.Inject

class InfoMusicRepository @Inject constructor (
    private val api : InfoMusicService,
    private val musicInfoDao: MusicInfoDao
)
{

    suspend fun getInfoMusicData(search:String,type:String,lim:Int,offset:Int):ResultsModel{
        val response:ResultsModel = api.getInfoMusicData(search,type, lim,offset)
        return response
    }

    suspend fun getUserDataFromDataBase(search:String):List<MusicInfo>{
        val response:List<MusicInfoEntity> = musicInfoDao.getMusicInfoData(search)
        return response.map { it.toDomain() }
    }

    suspend fun insertMusicInfoDataToDatabase(musicInfoData:List<MusicInfoEntity>){
        musicInfoDao.insertMusicInfoData(musicInfoData)
    }


}