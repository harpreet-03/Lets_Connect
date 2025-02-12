package com.example.connect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.connect.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            val name = binding.name.text.toString()
            val mobile = binding.mobile.text.toString().trim()
            val email = binding.email.text.toString().trim()

            if (name.isEmpty() || mobile.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val user = User(name, mobile, email)
            database = FirebaseDatabase.getInstance().getReference("Contact")
            database.child(mobile).setValue(user)
                .addOnSuccessListener {

                    Toast.makeText(this, "User Registration successfull!", Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "User Registration failed!", Toast.LENGTH_SHORT).show()
                }

        }

    }


    fun onSignupClick(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }


}