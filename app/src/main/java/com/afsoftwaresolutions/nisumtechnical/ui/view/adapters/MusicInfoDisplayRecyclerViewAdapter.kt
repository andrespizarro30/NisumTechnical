package com.afsoftwaresolutions.nisumtechnical.ui.view.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.afsoftwaresolutions.nisumtechnical.R
import com.afsoftwaresolutions.nisumtechnical.data.model.MusicInfoModel
import com.afsoftwaresolutions.nisumtechnical.databinding.MusicInfoBinding
import com.afsoftwaresolutions.nisumtechnical.ui.view.MainActivity
import com.afsoftwaresolutions.nisumtechnical.ui.view.dialogs.AlbumInfoDialog
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class MusicInfoDisplayRecyclerViewAdapter(
        var activity:Activity,
        var results: List<MusicInfoModel>
) : RecyclerView.Adapter<MusicInfoDisplayRecyclerViewAdapter.MusicInfoHolder>() {

    fun addNewData(newData: List<MusicInfoModel>){
        results = results + newData
        val a=1;
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MusicInfoDisplayRecyclerViewAdapter.MusicInfoHolder {
        //val binding = MusicInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //return MusicInfoHolder(binding.root)

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return MusicInfoHolder(layoutInflater.inflate(R.layout.music_info, parent, false))
    }

    override fun onBindViewHolder(
            holder: MusicInfoDisplayRecyclerViewAdapter.MusicInfoHolder,
            position: Int
    ) {
        val musicInfo:MusicInfoModel = results[position]
        holder.bind(activity,musicInfo)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class MusicInfoHolder(view: View):RecyclerView.ViewHolder(view){

        val binding = MusicInfoBinding.bind(view)
        lateinit var activity: Activity
        lateinit var musicInfoModel: MusicInfoModel

        init {
            itemView.setOnClickListener { it ->
                seeAlbumInfo(activity,musicInfoModel)
            }
        }

        fun bind(activity: Activity,musicInfoData: MusicInfoModel){

            this.activity=activity
            this.musicInfoModel=musicInfoData

            binding.tvArtistName.text = musicInfoData.artistName
            binding.tvTrackName.text = musicInfoData.trackName

            if(!musicInfoData.artworkUrl60.isNullOrEmpty()){
                Picasso.get().load(musicInfoData.artworkUrl60).into(binding.ivMusicInfo, object : Callback {
                    override fun onSuccess() {

                        /*
                        val bitmap = binding.ivMusicInfo.drawable.toBitmap()
                        var outStream: FileOutputStream? = null
                        val sdCard: File = Environment.getExternalStorageDirectory()
                        val dir = File(sdCard.getAbsolutePath().toString() + "/MusicImages")
                        dir.mkdirs()
                        val fileName = "Images_"+ musicInfoData.trackId.toString() + ".jpg"
                        val outFile = File(dir, fileName)
                        outStream = FileOutputStream(outFile)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
                        outStream.flush()
                        outStream.close()
                         */

                    }

                    override fun onError(e: Exception?) {

                    }
                })

            }

            binding.ivMusicInfo.scaleType = ImageView.ScaleType.FIT_XY

        }

        fun seeAlbumInfo(activity: Activity,musicInfoModel: MusicInfoModel){
            var albumInfoDialog = AlbumInfoDialog(activity,musicInfoModel)
            albumInfoDialog?.displayDialog()
        }

    }

}