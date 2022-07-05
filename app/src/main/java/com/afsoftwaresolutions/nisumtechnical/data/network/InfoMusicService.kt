package com.afsoftwaresolutions.nisumtechnical.data.network

import com.afsoftwaresolutions.nisumtechnical.data.model.ResultsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class InfoMusicService @Inject constructor (private val api:InfoMusicApiClient) {


    suspend fun getInfoMusicData(search:String,type:String,lim:Int,offset:Int):ResultsModel{
        return withContext(Dispatchers.IO){

            val response: Response<ResultsModel> = api.getMusicInfoData(search,type,lim,offset)
            response.body()!!

        }
    }


}