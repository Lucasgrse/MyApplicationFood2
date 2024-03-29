package com.example.myapplicationfood.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.example.myapplicationfood.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVariable()
        window.statusBarColor = Color.parseColor("#FFE4B5")
    }

    private fun setVariable() {
        binding.loginBtn.setOnClickListener {
            if (mAuth.currentUser != null) {
                startActivity(Intent(this@IntroActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@IntroActivity, LoginActivity::class.java))
            }
        }

        binding.signupBtn.setOnClickListener {
            startActivity(Intent(this@IntroActivity, SignupActivity::class.java))
        }
    }
}
