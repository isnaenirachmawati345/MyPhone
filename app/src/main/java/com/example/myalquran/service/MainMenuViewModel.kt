package com.example.myalquran.service

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainMenuViewModel : ViewModel() {
    var username: MutableLiveData<String> = MutableLiveData("username")

    fun  uncensored(){
        username.value = "Have a\nNice Day For You "
    }
}