package com.example.battlemanager.presentation.global.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.battlemanager.presentation.global.util.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    val rightMenu1 = SingleLiveEvent<Any>()
    val rightMenu1Enable = MutableLiveData(false)
    fun onRightMenu1(){
        rightMenu1.call()
    }
    val rightMenu2 = SingleLiveEvent<Any>()
    val rightMenu2Enable = MutableLiveData(false)
    fun onRightMenu2(){
        rightMenu2.call()
    }
    val leftMenu = SingleLiveEvent<Any>()
    val leftMenuEnable = MutableLiveData(true)
    fun onLeftMenu1(){
        leftMenu.call()
    }
}