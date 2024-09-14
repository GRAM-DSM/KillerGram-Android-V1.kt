package com.example.killergram_android_v1.feature.signup.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.FragmentEmailVerificationBinding
import java.util.regex.Pattern

class EmailVerificationFragment : Fragment() {

    private val binding by lazy {
        FragmentEmailVerificationBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onInputTextChanged()
        onInputFocusChanged()


    }

    private fun onInputTextChanged() {
        val value: String = binding.inputEmail.text.toString()
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        binding.inputEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! >= binding.inputEmailLayout.getCounterMaxLength())
                    binding.inputEmailLayout.error = ("Max character length is " + binding.inputEmailLayout.getCounterMaxLength())
                else if (Pattern.matches(s.toString(), emailPattern)) {
                    binding.inputEmailLayout.error = "이메일 형식이 맞지 않습니다!"
                }
                else
                    binding.inputEmailLayout.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun onInputFocusChanged() {


        binding.inputEmail.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.btnLogin.setBackgroundResource(R.drawable.bg_button_radius_0)
            } else {
                binding.btnLogin.setBackgroundResource(R.drawable.bg_button_radius_gray)
            }
        }
    }

    private fun fragmentTransaction() {

    }
}
