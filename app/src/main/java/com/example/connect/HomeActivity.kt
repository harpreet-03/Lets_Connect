package com.example.connect

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ActivityHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerViewContacts: RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var fabAddContact: FloatingActionButton
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Views
        recyclerViewContacts = findViewById(R.id.recyclerViewContacts)
        searchBar = findViewById(R.id.searchBar)
        fabAddContact = findViewById(R.id.fabAddContact)

        // Set up RecyclerView (Empty for now)
        recyclerViewContacts.layoutManager = LinearLayoutManager(this)

        // Floating Button Click -> Open Add Contact Screen
        fabAddContact.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }
    }
}
