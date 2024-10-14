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