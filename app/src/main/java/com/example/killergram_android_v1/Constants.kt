package com.example.killergram_android_v1

object Constants {
    object Key {
        const val USER_NAME = "userName"
        const val BASE_URL = "https://www.youtube.com"
    }

    object Common {
        const val INDEX_HOME = 0
    }

    object Regex {
        const val PASSWORD_REGEX = "[정규식]"
    }
}
data class Test(
    var a: Int,
    val b: Int,
)

fun test() {
    val test = Test(1, 1);
    test.a = 2;
}

// listOf("홈", "마이페이지", "즐겨찾기")
// index
// index == INDEX_HOME
// Regex.matches("사용자 입력값", "$PASSWORD_REGEX")
