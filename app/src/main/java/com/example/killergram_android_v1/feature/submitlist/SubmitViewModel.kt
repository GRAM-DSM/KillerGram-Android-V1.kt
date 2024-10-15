package com.example.killergram_android_v1.feature.submitlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SubmitViewModel: ViewModel() {
    private val _buttonState: MutableLiveData<Int> = MutableLiveData() // setter
    var buttonState: LiveData<Int> = _buttonState // getter

    private val _textState: MutableLiveData<String> = MutableLiveData()
    var textState: LiveData<String> = _textState

    fun onSelectItem(selectedButton: Int) {
        _buttonState.value = selectedButton
    }

    fun setLiveText(change: String) {
        _textState.value = change
    }
}