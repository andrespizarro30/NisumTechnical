package com.afsoftwaresolutions.nisumtechnical.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.afsoftwaresolutions.nisumtechnical.data.model.MusicInfoModel
import com.afsoftwaresolutions.nisumtechnical.data.model.ResultsModel
import com.afsoftwaresolutions.nisumtechnical.domain.GetInfoMusicDataUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ItunesSearcherViewModelTest{

    @RelaxedMockK
    private lateinit var getInfoMusicDataUseCase : GetInfoMusicDataUseCase

    private lateinit var itunesSearcherViewModel: ItunesSearcherViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        itunesSearcherViewModel = ItunesSearcherViewModel(getInfoMusicDataUseCase)

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `When the ViewModel is used on a search gets the music data from api`(){

        var resultModel = ResultsModel(resultCount = 0, listOf(MusicInfoModel("","",0,"","","","","","","","",0.0,0.0,0.0,0.0,0.0,0.0,"","","",0,"","","","","")))

        coEvery { getInfoMusicDataUseCase("somebody","music",20,0) } returns ResultsModel()

        itunesSearcherViewModel.getMusicInfo("somebody","music",20,0)

        assert(itunesSearcherViewModel.musicInfoLiveData.value == resultModel)

    }

}