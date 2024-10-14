package com.example.killergram_android_v1.feature.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityHomeBinding
import com.example.killergram_android_v1.feature.recyclerView.home.HomeAdapter
import com.example.killergram_android_v1.feature.recyclerView.home.data.Sport
import java.time.DayOfWeek
import java.time.LocalDate


class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this@HomeActivity)[HomeViewModel::class.java]
    }

    private var now = LocalDate.now()
    private var count = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        getDate()

        raiseRecycleView()
        observeTodaySportList()

        binding.imgLeftArrow.setOnClickListener(this)
        binding.imgRightArrow.setOnClickListener(this)
    }

    private fun raiseRecycleView() {
        val sportList: List<Sport> = listOf(
            Sport("축구", 16, 9, true)
        )
        homeViewModel.addSportList(sportList)
    }

    private fun observeTodaySportList() {
        homeViewModel.todaySportList.observe(this@HomeActivity) {
            val homeAdapter = HomeAdapter(it)
            val layoutManager = GridLayoutManager(this, 1)
            binding.recyclerSport.layoutManager = layoutManager
            binding.recyclerSport.adapter = homeAdapter
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.img_left_arrow -> {
                now = now.plusDays(7 * count--)
                setPreWeek(now)
            }
            R.id.img_right_arrow -> {
                now = now.plusDays(7 * count++)
                setNextWeek(now)
            }
        }
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
        }

        val (day1, day2, day3, day4, day5) = days
        val dateText1 = binding.tvDateFirst
        val dateText2 = binding.tvDateSecond
        val dateText3 = binding.tvDateThird
        val dateText4 = binding.tvDateFourth
        val dateText5 = binding.tvDateFifth

        dateText1.text = day1.toString()
        dateText2.text = day2.toString()
        dateText3.text = day3.toString()
        dateText4.text = day4.toString()
        dateText5.text = day5.toString()

        val today = LocalDate.now()

        // 현재 날짜에 맞게 text 색 변경
        if (today == now) { // todayDate의 요일을 구함 // 그 요일에 맞는 when에 들어가 text 색 변경
            when(now.dayOfWeek) {
                DayOfWeek.MONDAY -> {
                    dateText1.setTextColor(ContextCompat.getColor(baseContext, R.color.main))
                }
                DayOfWeek.TUESDAY -> {
                    dateText2.setTextColor(ContextCompat.getColor(baseContext, R.color.main))
                }
                DayOfWeek.WEDNESDAY -> {
                    dateText3.setTextColor(ContextCompat.getColor(baseContext, R.color.main))
                }
                DayOfWeek.THURSDAY -> {
                    dateText4.setTextColor(ContextCompat.getColor(baseContext, R.color.main))
                }
                DayOfWeek.FRIDAY -> {
                    dateText5.setTextColor(ContextCompat.getColor(baseContext, R.color.main))
                }
                else -> {}
            }
        }
    }

    fun setNextWeek(week: LocalDate): MutableList<Int> {
        var now = week
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
        }

        return days
    }

    fun setPreWeek(week: LocalDate): MutableList<Int> {
        var now = week
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
        }

        return days
    }
}
