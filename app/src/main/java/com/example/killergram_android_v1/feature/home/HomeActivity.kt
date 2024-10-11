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
import java.time.LocalDate
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

    private fun getDate() {
        var now = LocalDate.now()
        val weeks = DayOfWeek.entries.toList()

        val days = mutableListOf<Int>()

        when (now.dayOfWeek) {
            in DayOfWeek.MONDAY..DayOfWeek.FRIDAY -> {
                now = now.minusDays(weeks.indexOf(now.dayOfWeek).toLong())
            }

            DayOfWeek.SATURDAY -> {
                now = now.plusDays(2)
            }

            DayOfWeek.SUNDAY -> {
                now = now.plusDays(1)
            }

            else -> {}
        }

        repeat(5) {
            now = now.plusDays(1)
            days.add(now.dayOfMonth - 1)
            binding.tvDateFirst.setText(now.toString()).toString()
        }
    }
}
