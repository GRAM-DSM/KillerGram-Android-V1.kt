package com.example.killergram_android_v1.feature.utils

import android.icu.util.Calendar
import java.time.DayOfWeek
import java.time.LocalDate


fun isRegexEmail(email: String): Boolean {
    return email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$".toRegex())
}

fun isRegexPassword(password: String): Boolean {
    // 8~16글자, 대문자 1개, 소문자 1개, 숫자 1개
    return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,16}$".toRegex())
}

fun initialThisWeek(): MutableList<Int> {
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

    return days
}

