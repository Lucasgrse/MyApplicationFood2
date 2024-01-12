package com.example.myapplicationfood.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplicationfood.R

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

open class BaseActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    val TAG = "uilover"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        mAuth = FirebaseAuth.getInstance()

        window.statusBarColor = resources.getColor(R.color.white)
    }
}
