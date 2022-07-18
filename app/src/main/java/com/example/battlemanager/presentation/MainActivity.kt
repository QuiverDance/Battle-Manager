package com.example.battlemanager.presentation

import android.os.Bundle
import com.example.battlemanager.R
import com.example.battlemanager.databinding.ActivityMainBinding
import com.example.battlemanager.global.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResourceId = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}