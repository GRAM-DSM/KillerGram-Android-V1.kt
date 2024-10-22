package com.example.killergram_android_v1.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.killergram_android_v1.feature.recyclerView.home.data.Sport

class HomeViewModel : ViewModel() {
    // 오늘의 스포츠 컴포넌트
    // 캘린더
    // 운동 신청 컴포넌트 - text 변경

    private val _todaySportList: MutableLiveData<List<Sport>> = MutableLiveData()
    val todaySportList: LiveData<List<Sport>> = _todaySportList

    fun addSportList(sport: List<Sport>) { // 요일, 날짜
        _todaySportList.value = sport // 서버연동 후 값 적용
    }

}
