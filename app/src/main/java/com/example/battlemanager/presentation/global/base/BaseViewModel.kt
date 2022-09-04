package com.example.battlemanager.presentation.global.base

import androidx.lifecycle.ViewModel
import com.example.battlemanager.presentation.global.util.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    val rightMenu1 = SingleLiveEvent<Any>()
    fun onRightMenu1(){
        rightMenu1.call()
    }
    val rightMenu2 = SingleLiveEvent<Any>()
    fun onRightMenu2(){
        rightMenu2.call()
    }
    val leftMenu = SingleLiveEvent<Any>()
    fun onLeftMenu1(){
        leftMenu.call()
    }
}