package com.afsoftwaresolutions.nisumtechnical.data.model

import com.google.gson.annotations.SerializedName

data class ResultsModel(
        @SerializedName("resultCount") var resultCount : Int = 0,
        @SerializedName("results") var results : List<MusicInfoModel> = listOf(MusicInfoModel())
)
