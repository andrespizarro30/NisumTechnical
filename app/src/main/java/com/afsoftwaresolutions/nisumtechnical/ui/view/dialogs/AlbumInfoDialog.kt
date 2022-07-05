package com.afsoftwaresolutions.nisumtechnical.ui.view.dialogs

import android.app.Activity
import android.net.Uri
import android.view.View
import android.widget.MediaController
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.afsoftwaresolutions.nisumtechnical.R
import com.afsoftwaresolutions.nisumtechnical.data.model.MusicInfoModel
import com.afsoftwaresolutions.nisumtechnical.databinding.ActivityMainBinding
import com.afsoftwaresolutions.nisumtechnical.databinding.AlbumInfoBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class AlbumInfoDialog{

    lateinit var mDialog : AlertDialog
    lateinit var alertDialogBuilder : AlertDialog.Builder
    lateinit var progressThread : Thread
    private lateinit var binding : AlbumInfoBinding
    var activity : Activity
    var musicInfoModel:MusicInfoModel

    constructor(activity: Activity,musicInfoModel:MusicInfoModel) {
        this.activity=activity
        this.musicInfoModel = musicInfoModel
    }

    fun displayDialog(){

        alertDialogBuilder = activity.let { AlertDialog.Builder(it) }

        binding = AlbumInfoBinding.inflate(activity.layoutInflater)

        binding.dialog = this

        alertDialogBuilder?.setView(binding.root)

        if(!musicInfoModel.artworkUrl100.isNullOrEmpty()){
            Picasso.get().load(musicInfoModel.artworkUrl100).into(binding.ivAlbumPhoto, object : Callback {
                override fun onSuccess() {

                }

                override fun onError(e: Exception?) {

                }
            })

        }
        binding.tvAlbumName.text = musicInfoModel.kind
        binding.tvBandName.text = musicInfoModel.artistName

        if(!musicInfoModel.previewUrl.isNullOrEmpty()){
            binding.vvTrackVideo.setVideoURI(Uri.parse(musicInfoModel.previewUrl))
            binding.vvTrackVideo.setOnPreparedListener{
                binding.pbCountTime.max = binding.vvTrackVideo.duration
                val vDuration =binding.vvTrackVideo.duration.toLong()
                val duration = SimpleDateFormat("mm:ss").format(Date(vDuration))
                binding.tvCountTime.text = "00:00/"+ duration +""
                binding.btnPlay.isVisible = true
                binding.btnStop.isVisible = true
            }
        }

        alertDialogBuilder?.setCancelable(false)

        mDialog = alertDialogBuilder?.create()
        mDialog?.show()
    }

    fun onPlay(view: View){

        progressThread = Thread (
                Runnable {
                    kotlin.run {

                        binding.vvTrackVideo.start()

                        try {

                            val vDuration =binding.vvTrackVideo.duration.toLong()
                            val duration = SimpleDateFormat("mm:ss").format(Date(vDuration))

                            var vCurrentTime : Long= 0
                            var currentTime = ""

                            while (binding.vvTrackVideo.isPlaying) {
                                vCurrentTime = binding.vvTrackVideo.currentPosition.toLong()
                                currentTime = SimpleDateFormat("mm:ss").format(Date(vCurrentTime))
                                binding.pbCountTime.progress = binding.vvTrackVideo.currentPosition
                                binding.tvCountTime.text = ""+ currentTime +"/"+ duration +""
                                Thread.sleep(1000)
                            }
                            progressThread.interrupt()
                        }catch (e:InterruptedException){
                            progressThread.interrupt()
                        }
                    }
                })

        progressThread.start()

    }

    fun onStop(view: View){
        try {
            binding.vvTrackVideo.pause()
            progressThread.interrupt()
        }catch (e:Exception) {

        }
    }

    fun onClose(view: View){
        binding.vvTrackVideo.stopPlayback()
        mDialog.cancel()
        mDialog.dismiss()
    }
}