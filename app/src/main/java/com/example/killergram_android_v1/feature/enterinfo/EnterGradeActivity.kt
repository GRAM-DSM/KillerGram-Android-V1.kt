package com.example.killergram_android_v1.feature.enterinfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityEnterGradeBinding
import com.example.killergram_android_v1.feature.enterinfo.endterGender.EnterGenderActivity

class EnterGradeActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEnterGradeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.imgLeftArrow.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val enterGradeToEnterGender = Intent(this, EnterGenderActivity::class.java)
        val enterGradeToEnterName = Intent(this, EnterNameActivity::class.java)
        val pref = this.getSharedPreferences("grade", Context.MODE_PRIVATE)
        val editor = pref.edit()
        Log.d("TEST", pref.getString("ability", "")!! + "\n"
                + pref.getString("email", "")!! + "\n"
                + pref.getString("gender", "")!! + "\n"
                + pref.getString("name", "")!! + "\n"
                + pref.getString("grade", "") + "\n"
                + pref.getString("password", ""))
        when(v?.id) {
            R.id.btn_login -> {
                editor.putString("grade", binding.tieGrade.text.toString())
                editor.apply()
                startActivity(enterGradeToEnterGender)
            }
            R.id.img_left_arrow -> {
                startActivity(enterGradeToEnterName)
            }
        }
    }
}