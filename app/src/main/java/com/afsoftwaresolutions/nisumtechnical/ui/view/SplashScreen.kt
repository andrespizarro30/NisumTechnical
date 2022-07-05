package com.afsoftwaresolutions.nisumtechnical.ui.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.afsoftwaresolutions.nisumtechnical.R
import com.afsoftwaresolutions.nisumtechnical.databinding.SplashScreenBinding
import com.bumptech.glide.Glide

class SplashScreen : AppCompatActivity() {

    private lateinit var binding : SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(R.drawable.itunes_presenter).into(binding.ivLogo)

        val handler: Handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            val intent: Intent = Intent(this, MainActivity::class.java)
            val bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            startActivity(intent,bundle)
            finish()
        },9000)

    }

}