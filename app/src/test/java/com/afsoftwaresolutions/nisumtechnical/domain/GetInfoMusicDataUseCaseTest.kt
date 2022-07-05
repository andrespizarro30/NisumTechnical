package com.afsoftwaresolutions.nisumtechnical.domain

import com.afsoftwaresolutions.nisumtechnical.data.InfoMusicRepository
import com.afsoftwaresolutions.nisumtechnical.data.database.entities.toDatabase
import com.afsoftwaresolutions.nisumtechnical.data.model.MusicInfoModel
import com.afsoftwaresolutions.nisumtechnical.data.model.ResultsModel
import com.afsoftwaresolutions.nisumtechnical.domain.model.toDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import javax.inject.Named

class GetInfoMusicDataUseCaseTest{

    @RelaxedMockK
    private lateinit var repository : InfoMusicRepository

    private var checkConnectivity: Boolean = true

    lateinit var getInfoMusicDataUseCase : GetInfoMusicDataUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getInfoMusicDataUseCase = GetInfoMusicDataUseCase(repository,checkConnectivity)
    }

    @Test
    fun `When the api return zero items get data from database`() = runBlocking{

        coEvery { repository.getInfoMusicData("somebody","music",20,0) } returns ResultsModel()

        getInfoMusicDataUseCase("somebody","music",20,0)

        coVerify(exactly = 1) { repository.getUserDataFromDataBase("somebody") }

    }

    @Test
    fun `When the api return something items get data from api`() = runBlocking{

        var resultModel = ResultsModel(resultCount = 1, listOf(MusicInfoModel("","",0,"","","","","","","","",0.0,0.0,0.0,0.0,0.0,0.0,"","","",0,"","","","","")))

        coEvery { repository.getInfoMusicData("somebody","music",20,0) } returns resultModel

        val response = getInfoMusicDataUseCase("somebody","music",20,0)

        coVerify(exactly = 1) { repository.insertMusicInfoDataToDatabase(resultModel.results.map { it.toDomain().toDatabase()}) }

        coVerify(exactly = 0) { repository.getUserDataFromDataBase("somebody") }

        assert(resultModel == response)
    }



}