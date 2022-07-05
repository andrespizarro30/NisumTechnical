package com.afsoftwaresolutions.nisumtechnical.data.model

import com.afsoftwaresolutions.nisumtechnical.domain.model.MusicInfo
import com.google.gson.annotations.SerializedName

data class MusicInfoModel(
    @SerializedName("wrapperType") val wrapperType : String = "",
    @SerializedName("kind") val kind : String= "",
    @SerializedName("trackId") val trackId : Int=0,
    @SerializedName("artistName") val artistName : String= "",
    @SerializedName("trackName") val trackName : String= "",
    @SerializedName("trackCensoredName") val trackCensoredName : String= "",
    @SerializedName("trackViewUrl") val trackViewUrl : String= "",
    @SerializedName("previewUrl") val previewUrl : String= "",
    @SerializedName("artworkUrl30") val artworkUrl30 : String= "",
    @SerializedName("artworkUrl60") val artworkUrl60 : String= "",
    @SerializedName("artworkUrl100") val artworkUrl100 : String= "",
    @SerializedName("collectionPrice") val collectionPrice : Double=0.0,
    @SerializedName("trackPrice") val trackPrice : Double=0.0,
    @SerializedName("trackRentalPrice") val trackRentalPrice : Double=0.0,
    @SerializedName("collectionHdPrice") val collectionHdPrice : Double=0.0,
    @SerializedName("trackHdPrice") val trackHdPrice : Double=0.0,
    @SerializedName("trackHdRentalPrice") val trackHdRentalPrice : Double=0.0,
    @SerializedName("releaseDate") val releaseDate : String= "",
    @SerializedName("collectionExplicitness") val collectionExplicitness : String= "",
    @SerializedName("trackExplicitness") val trackExplicitness : String= "",
    @SerializedName("trackTimeMillis") val trackTimeMillis : Int=0,
    @SerializedName("country") val country : String= "",
    @SerializedName("currency") val currency : String= "",
    @SerializedName("primaryGenreName") val primaryGenreName : String= "",
    @SerializedName("contentAdvisoryRating") val contentAdvisoryRating : String= "",
    @SerializedName("longDescription") val longDescription : String= ""
)

fun MusicInfo.toModel() = MusicInfoModel(trackId = trackId,artistName = artistName,trackName = trackName,trackCensoredName = trackCensoredName,trackViewUrl = trackViewUrl,previewUrl = previewUrl,artworkUrl30 = artworkUrl30,artworkUrl60 = artworkUrl60,artworkUrl100 = artworkUrl100)
