package com.example.projectmanage.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmanage.R
import com.example.projectmanage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)


    }
    private fun setupActionBar(){
//        setSupportActionBar(binding.toolbarMainActivity)
//        binding.toolbarMainActivity.setNavigationIcon(R.drawable.baseline_menu_24)

    }
}