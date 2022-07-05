package com.afsoftwaresolutions.nisumtechnical.domain

import android.content.Context
import com.afsoftwaresolutions.nisumtechnical.data.InfoMusicRepository
import com.afsoftwaresolutions.nisumtechnical.data.database.entities.toDatabase
import com.afsoftwaresolutions.nisumtechnical.data.model.MusicInfoModel
import com.afsoftwaresolutions.nisumtechnical.data.model.ResultsModel
import com.afsoftwaresolutions.nisumtechnical.data.model.toModel
import com.afsoftwaresolutions.nisumtechnical.domain.model.MusicInfo
import com.afsoftwaresolutions.nisumtechnical.domain.model.toDomain
import com.afsoftwaresolutions.nisumtechnical.ui.view.MainActivity
import com.afsoftwaresolutions.nisumtechnical.utils.CheckConnectivity
import com.afsoftwaresolutions.nisumtechnical.utils.CheckConnectivity_
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.coroutineContext

class GetInfoMusicDataUseCase @Inject constructor (
        private val repository : InfoMusicRepository,
        @Named("checkConnection") private val checkConnectivity: Boolean
        ) {

    suspend operator fun invoke(search:String,type:String,lim:Int,offset:Int):ResultsModel{

        var musicInfo = ResultsModel()
        val checkConnectivity_=CheckConnectivity_()

        if(checkConnectivity_.invoke(MainActivity.context)){
            musicInfo = repository.getInfoMusicData(search, type, lim, offset)

            if(musicInfo.resultCount>0){
                val musicInfoModel : List<MusicInfoModel> = musicInfo.results
                val musicInfoList:List<MusicInfo> = musicInfoModel.map { it.toDomain() }
                repository.insertMusicInfoDataToDatabase(musicInfoList.map{it.toDatabase()})
            }else{
                val musicInfoList:List<MusicInfo> = repository.getUserDataFromDataBase(search)
                musicInfo.resultCount = musicInfoList.size
                musicInfo.results = musicInfoList.map { it.toModel() }
            }
        }else{
            val musicInfoList:List<MusicInfo> = repository.getUserDataFromDataBase(search)
            musicInfo.resultCount = musicInfoList.size
            musicInfo.results = musicInfoList.map { it.toModel() }
        }

        return musicInfo
    }

}