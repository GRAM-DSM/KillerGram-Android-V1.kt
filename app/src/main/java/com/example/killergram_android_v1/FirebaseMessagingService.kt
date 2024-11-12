package com.example.killergram_android_v1

import android.util.Log
import com.example.killergram_android_v1.feature.login.LoginActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TEST", "onNewToken: $token")
        // 새로운 토큰 수신 시 서버로 전송
    }

    // Foreground에서 Push Service를 받기 위해 Notification 설정
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

    }
}