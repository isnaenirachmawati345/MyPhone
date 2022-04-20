package com.example.myalquran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.example.myalquran.databinding.ActivitySplashBinding

class ActivitySplash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@ActivitySplash,MainActivity::class.java))
            finish()
        }, 2000)
    }
}