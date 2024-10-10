package com.example.killergram_android_v1.feature.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityHomeBinding
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)


    }

    private fun getDate()  { // 요일, 날짜
        val now =  System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat("EEEE", Locale.KOREA).format(now)
        val simpleDateFormatDate = SimpleDateFormat("dd", Locale.KOREA).format(now)
        //val date: String = Date(simpleDateFormat, simpleDateFormatDate).toString()

    }
}
