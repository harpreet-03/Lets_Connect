package com.example.connect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.connect.databinding.ActivityLoginBinding
import com.example.connect.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button3.setOnClickListener {
            val name = binding.name2.text.toString()
            val mobile = binding.mobile2.text.toString()

            if (name.isEmpty() || mobile.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                readData(name, mobile)
            }
        }
    }

    fun readData(name: String, mobile: String) {
        if (mobile.isBlank() || name.isBlank()) {
            Toast.makeText(this, "Name and Mobile number cannot be empty!", Toast.LENGTH_SHORT)
                .show()
            return
        }

        database = FirebaseDatabase.getInstance().getReference("Contact")
        database.child(mobile.trim()).get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val storedName = snapshot.child("name").value.toString()

                    if (storedName.equals(name, ignoreCase = true)) {
                        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

                        // Navigate to HomeActivity
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish() // Prevents going back to Login screen
                    } else {
                        Toast.makeText(
                            this,
                            "User exists, but name does not match!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this, "User does not exist!", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "Error retrieving data, please try again later",
                    Toast.LENGTH_LONG
                ).show()
            }
    }



    fun onSignupClick(view: View) {
        val intent = Intent(this, signUp::class.java)
        startActivity(intent)
    }
}