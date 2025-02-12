package com.example.connect

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.connect.databinding.ActivityLetsConnectBinding

class letsConnect : AppCompatActivity() {
    lateinit var binding: ActivityLetsConnectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLetsConnectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
        val intent = Intent(this, signUp::class.java)
            startActivity(intent)
        }


    }
}