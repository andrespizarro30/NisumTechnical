package com.afsoftwaresolutions.nisumtechnical.ui.view.fragments

import android.app.Activity
import android.app.ProgressDialog
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afsoftwaresolutions.nisumtechnical.R
import com.afsoftwaresolutions.nisumtechnical.data.model.MusicInfoModel
import com.afsoftwaresolutions.nisumtechnical.databinding.MusicInfoDisplayFragmentBinding
import com.afsoftwaresolutions.nisumtechnical.ui.view.MainActivity
import com.afsoftwaresolutions.nisumtechnical.ui.view.adapters.MusicInfoDisplayRecyclerViewAdapter
import com.afsoftwaresolutions.nisumtechnical.ui.viewmodel.ItunesSearcherViewModel

class DisplayMusicInfoFragment : Fragment() {

    lateinit var binding : MusicInfoDisplayFragmentBinding

    private val itunesSearcherViewModel : ItunesSearcherViewModel by activityViewModels()

    private var adapter: MusicInfoDisplayRecyclerViewAdapter? = null

    private lateinit var progress: ProgressDialog

    fun newInstance(): DisplayMusicInfoFragment {
        val fragmento = DisplayMusicInfoFragment()
        return fragmento
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MusicInfoDisplayFragmentBinding.inflate(inflater, container, false)

        binding.fragment = this
        binding.iTunesSearcherViewModel = itunesSearcherViewModel
        binding.setLifecycleOwner(this)

        binding.rvMusicInfoDisplay.layoutManager = LinearLayoutManager(context)

        progress = ProgressDialog(context)
        progress?.setMessage(resources.getString(R.string.searching_music_info))
        progress?.setCanceledOnTouchOutside(false)

        itunesSearcherViewModel.isLoading.observe(this.viewLifecycleOwner,{
            binding.pbSearchMusicData.isVisible = it

            /*
            if(it){
                progress.show()
            }else{
                progress.dismiss()
            }
             */

        })

        itunesSearcherViewModel.musicInfoLiveData.observe(this.viewLifecycleOwner,{ resultModel ->

            if(resultModel.resultCount>0){
                if(adapter==null){
                    loadMusicInfoRecyclerView(resultModel.results)
                }else{
                    adapter?.addNewData(resultModel.results)
                    adapter?.notifyDataSetChanged()
                }
                Toast.makeText(context,resultModel.resultCount.toString(),Toast.LENGTH_SHORT).show()
            }else{
                loadMusicInfoRecyclerView(listOf(MusicInfoModel()))
            }

        })



        binding.svSearchMusicInfo.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(search: String): Boolean {
                adapter=null
                itunesSearcherViewModel.getMusicInfo(search,"music",20,0)
                return false
            }
            override fun onQueryTextChange(search: String): Boolean {
                return false
            }
        })

        binding.rvMusicInfoDisplay.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(!recyclerView.canScrollVertically(1) && itunesSearcherViewModel.musicInfoLiveData.value!!.results.size>0 && newState.equals(1)){
                    val search : String = itunesSearcherViewModel.dataMusicCurrentSearch.value!!
                    var offset : Int = itunesSearcherViewModel.dataMusicCurrentOffset.value!!
                    offset = offset + 20
                    itunesSearcherViewModel.getMusicInfo(search,"music",20,offset)
                }
            }
        })


        return binding.root
    }

    fun loadMusicInfoRecyclerView(musicInfoData: List<MusicInfoModel>){
        adapter = MusicInfoDisplayRecyclerViewAdapter(activity as MainActivity,musicInfoData)
        binding.rvMusicInfoDisplay.adapter = adapter
    }
}