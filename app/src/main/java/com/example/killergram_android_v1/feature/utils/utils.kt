package com.example.killergram_android_v1.feature.utils

import android.content.Context
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

fun dpToPx(context: Context, dp: Int): Int {
    return (dp * context.resources.displayMetrics.density).toInt()
}

