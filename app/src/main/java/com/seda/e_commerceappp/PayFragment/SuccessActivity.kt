package com.seda.e_commerceappp.PayFragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seda.e_commerceappp.Dashboard.dashboardActivity
import com.seda.e_commerceappp.R
import com.seda.e_commerceappp.databinding.ActivitySuccessBinding
import com.seda.e_commerceappp.databinding.FragmentGoToPayBinding

class SuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.shop.setOnClickListener {
            val intent= Intent(this,dashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}