package com.seda.e_commerceappp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.seda.e_commerceappp.Loginİslemler.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer =   object : CountDownTimer(1800, 3000) {
            override fun onFinish() {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)

            }

            override fun onTick(p0: Long) {
                Log.d("SplashActivity", p0.toString())
            }
        }
        timer.start()
    }
    }
