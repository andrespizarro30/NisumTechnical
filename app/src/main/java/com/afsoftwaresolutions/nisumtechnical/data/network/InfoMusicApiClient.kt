package com.afsoftwaresolutions.nisumtechnical.data.network

import com.afsoftwaresolutions.nisumtechnical.data.model.ResultsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface InfoMusicApiClient {

    @GET("/search")
    suspend fun getMusicInfoData(@Query("term") search:String, @Query("mediaType") type:String, @Query("limit") lim:Int,@Query("offset") offset:Int):Response<ResultsModel>


}