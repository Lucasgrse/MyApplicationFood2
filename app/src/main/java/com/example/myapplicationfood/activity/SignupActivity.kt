package com.example.myapplicationfood.activity

import com.example.myapplicationfood.databinding.ActivitySignupBinding
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast


class SignupActivity : BaseActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVariable()
    }

    private fun setVariable() {
        binding.signupBtn.setOnClickListener {
            val email = binding.userEdt.text.toString()
            val password = binding.passEdt.text.toString()

            if (password.length < 6) {
                Toast.makeText(this@SignupActivity, "Your password must be 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this@SignupActivity) { task ->
                if (task.isSuccessful) {
                    Log.i(TAG, "onComplete: ")
                    startActivity(Intent(this@SignupActivity, MainActivity::class.java))
                } else {
                    Log.i(TAG, "failure: " + task.exception)
                    Toast.makeText(this@SignupActivity, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
