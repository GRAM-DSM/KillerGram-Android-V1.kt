package com.example.killergram_android_v1.feature.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.data.api.ApiProvider
import com.example.killergram_android_v1.data.response.sport.GetSportResponse
import com.example.killergram_android_v1.databinding.ActivityHomeBinding
import com.example.killergram_android_v1.feature.recyclerView.home.HomeAdapter
import com.example.killergram_android_v1.feature.submitlist.SubmitActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.DayOfWeek
import java.time.LocalDate

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val sportList: MutableList<GetSportResponse> = mutableListOf()

    private val dateList: MutableList<GetSportResponse> by lazy {
        mutableListOf()
    }
    private val homeAdapter = HomeAdapter(sportList) {
        val intent = Intent(this, SubmitActivity::class.java)
        startActivity(intent)
    }

    private var now = LocalDate.now()

    private val retrofit = ApiProvider.getSportApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarHome)
        binding.toolbarHome.contentInsetStartWithNavigation = 0
        supportActionBar?.title = ""

        binding.toolbarHome.overflowIcon = getDrawable(R.drawable.ic_setting)

        binding.frameContent.visibility = View.INVISIBLE

        with(intent) {
            getStringExtra("sportName")?.run {
                sportList.add(
                    GetSportResponse(
                        createdDate = "",
                        managerEmail = "",
                        personnel = 0,
                        position = true,
                        sportId = "",
                        sportName = this,
                        enabled = false,
                        currentPersonnel = 2
                    )
                )
            }
        }

        getDate()
        calenderEventFilter()

        observeTodaySportList()

        getSportListToServer()

        binding.imgBtnHomeSoccer.setOnClickListener(this)

        binding.imgLeftArrow.setOnClickListener(this)
        binding.imgRightArrow.setOnClickListener(this)
        binding.constraintSportComponent.setOnClickListener(this)

        binding.imgBtnHomeTableTennis.setOnClickListener(this)
        binding.imgBtnHomeSoccer.setOnClickListener(this)
        binding.imgBtnHomeFitness.setOnClickListener(this)
        binding.imgBtnHomeTableTennis.setOnClickListener(this)
    }

    private fun observeTodaySportList() {
        val layoutManager = GridLayoutManager(this, 1)
        binding.recyclerSport.layoutManager = layoutManager
        binding.recyclerSport.adapter = homeAdapter
    }

    override fun onClick(v: View?) {
        val homeToSubmit = Intent(this, SubmitActivity::class.java)

        val dayList = listOf(
            binding.tvDateFirst,
            binding.tvDateSecond,
            binding.tvDateThird,
            binding.tvDateFourth,
            binding.tvDateFifth,
        )

        when (v?.id) {
            R.id.img_left_arrow -> {
                now = now.minusWeeks(1)
                setPreWeek(now).run {
                    repeat(this.size) {
                        if (this[it] != 0) {
                            dayList[it].text = this[it].toString()
                        } else {
                            dayList[it].text = 31.toString()
                        }
                    }
                }
            }

            R.id.img_right_arrow -> {
                now = now.plusWeeks(1)
                val days = setNextWeek(now)
                repeat(days.size) {
                    if (days[it] != 0) {
                        dayList[it].text = days[it].toString()
                    } else {
                        dayList[it].text = 31.toString()
                    }
                }
            }
            R.id.constraint_sport_component -> {
                startActivity(homeToSubmit)
            }
            R.id.recycler_sport -> {
                startActivity(homeToSubmit)
            }
            R.id.img_btn_home_tableTennis -> {
                homeToSubmit.putExtra("homeSport", "탁구")
                startActivity(homeToSubmit)
            }
            R.id.img_btn_home_fitness -> {
                homeToSubmit.putExtra("homeSport", "헬스")
            }
            R.id.img_btn_home_baseball -> {
                homeToSubmit.putExtra("homeSport", "야구")
            }
            R.id.img_btn_home_soccer -> {
                homeToSubmit.putExtra("homeSport", "축구")
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

        val today = LocalDate.now()

        dateText1.text = day1.toString()
        dateText2.text = day2.toString()
        dateText3.text = day3.toString()
        dateText4.text = day4.toString()
        dateText5.text = day5.toString()

        val calendarDate = now.minusDays(1)

        // 현재 날짜에 맞게 text 색 변경
        // Log.d("TEST4", today.toString() + calendarDate.minusDays(1).toString())
        if (today == calendarDate) { // todayDate의 요일을 구함 // 그 요일에 맞는 when에 들어가 text 색 변경
            when (today.dayOfWeek) {
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

    private fun setNextWeek(week: LocalDate): MutableList<Int> {
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

    private fun setPreWeek(week: LocalDate): MutableList<Int> {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_item_log_out -> {
                Toast.makeText(this, "로그인 선택 됨", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_item_setting -> {
                Toast.makeText(this, "설정 선택 됨", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_item_out_membership -> {
                Toast.makeText(this, "고객센터 선택 됨", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getSportListToServer() {
        val accessToken = this.getSharedPreferences("token", Context.MODE_PRIVATE).getString("access_token", "")!!

        retrofit.getSport(
            accessToken = "Bearer $accessToken"
        ).enqueue(object : Callback<List<GetSportResponse>> {
            override fun onResponse(
                call: Call<List<GetSportResponse>>,
                response: Response<List<GetSportResponse>>
            ) {
                when(response.code()) {
                    200 -> {
                        val list = response.body()!!

                        val firstDateList = list.filter {
                            it.createdDate.substring(8 until 10) == binding.tvDateFirst.text.toString()
                        }
                        dateList.addAll(firstDateList)

                        homeAdapter.addList(list)
                        homeAdapter.notifyDataSetChanged()
                    }
                    else -> {
                        Log.d("TEST2", response.code().toString())
                    }
                }
            }

            override fun onFailure(call: Call<List<GetSportResponse>>, t: Throwable) {

            }
        })
    }

    private fun calenderEventFilter() {
        with(binding) {
            tvDateFirst.setOnClickListener {
                homeAdapter.filter.filter(tvDateFirst.text)
            }
            
        }
    }
}
