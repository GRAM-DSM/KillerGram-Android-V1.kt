package com.example.killergram_android_v1.feature.findpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.killergram_android_v1.databinding.FragmentFindVerifyEmailCodeBinding

class FindVerifyEmailCodeFragment : Fragment() {
    private val binding by lazy {
        FragmentFindVerifyEmailCodeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }
}