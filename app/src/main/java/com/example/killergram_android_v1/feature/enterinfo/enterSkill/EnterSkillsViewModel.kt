package com.example.killergram_android_v1.feature.enterinfo.enterSkill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EnterSkillsViewModel : ViewModel() {
    private val _buttonState: MutableLiveData<Int> = MutableLiveData() // setter
    var buttonState: LiveData<Int> = _buttonState // getter

    fun onSelectItem(selectedButton: Int) {
        _buttonState.value = selectedButton
    }
}

// 맨 처음에 -1

// activity에서 observe
// observe 안에서 when(number)
// 0 -> {
// 0 on 1 false 2 false
// 1-> 1 true 0 false 2 false
// 2-> 0 false 1 false 2 true
//
//}