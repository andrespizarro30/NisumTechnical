package com.afsoftwaresolutions.nisumtechnical.ui.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.afsoftwaresolutions.nisumtechnical.R
import com.afsoftwaresolutions.nisumtechnical.databinding.ActivityMainBinding
import com.afsoftwaresolutions.nisumtechnical.ui.view.fragments.DisplayMusicInfoFragment
import com.afsoftwaresolutions.nisumtechnical.ui.viewmodel.ItunesSearcherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var context:Context
    }

    private lateinit var binding : ActivityMainBinding

    private var FRAGMENT_TAG = "FRAGMENT_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayFirstFragment(savedInstanceState)

        context=this

    }

    private fun displayFirstFragment(savedInstanceState: Bundle?) {

        if(savedInstanceState==null){
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragment: DisplayMusicInfoFragment = DisplayMusicInfoFragment().newInstance()
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.flMusicInfoDisplay, fragment, FRAGMENT_TAG)
            fragmentTransaction.commit()
        }else{
            val fragment: Fragment? = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)
            if(fragment!=null){
                val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(
                    R.id.flMusicInfoDisplay,
                    fragment,
                    FRAGMENT_TAG
                )
                fragmentTransaction.commit()
            }
        }
    }
}