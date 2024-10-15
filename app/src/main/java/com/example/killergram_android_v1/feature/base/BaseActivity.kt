package com.example.killergram_android_v1.feature.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<Binding: ViewDataBinding> : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {

    }

    override fun onClick(v: View?) {

    }
}