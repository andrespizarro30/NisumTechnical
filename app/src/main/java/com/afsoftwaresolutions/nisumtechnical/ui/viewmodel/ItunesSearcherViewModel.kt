package com.afsoftwaresolutions.nisumtechnical.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afsoftwaresolutions.nisumtechnical.data.model.MusicInfoModel
import com.afsoftwaresolutions.nisumtechnical.data.model.ResultsModel
import com.afsoftwaresolutions.nisumtechnical.domain.GetInfoMusicDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItunesSearcherViewModel @Inject constructor(val getInfoMusicDataUseCase : GetInfoMusicDataUseCase) : ViewModel() {

    val musicInfoLiveData = MutableLiveData<ResultsModel>()
    val isLoading = MutableLiveData<Boolean>()

    val dataMusicCurrentSearch = MutableLiveData<String>()
    val dataMusicCurrentOffset = MutableLiveData<Int>()


    fun getMusicInfo(search:String,type:String,lim:Int,offset:Int){
        viewModelScope.launch {

            dataMusicCurrentSearch.value = search
            dataMusicCurrentOffset.value = offset

            isLoading.postValue(true)

            val result:ResultsModel = getInfoMusicDataUseCase(search, type, lim, offset)
            if(result.resultCount>0){
                musicInfoLiveData.postValue(result)
            }else{
                val musicInfoList:ResultsModel = ResultsModel()
                musicInfoLiveData.postValue(musicInfoList)
            }

            isLoading.postValue(false)

        }
    }

}