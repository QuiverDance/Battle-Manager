package com.example.battlemanager.presentation.main

import com.example.battlemanager.R
import com.example.battlemanager.databinding.ActivityMainBinding
import com.example.battlemanager.presentation.global.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResourceId = R.layout.activity_main
}