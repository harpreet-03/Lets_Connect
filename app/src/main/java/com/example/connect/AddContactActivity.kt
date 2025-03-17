package com.example.letsconnect

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.connect.Contact
import com.example.connect.ContactDatabase
import com.example.connect.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddContactActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var buttonSave: Button
    private lateinit var contactDatabase: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        // Initialize views
        editTextName = findViewById(R.id.editTextName)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSave = findViewById(R.id.buttonSave)

        // Initialize Room database
        contactDatabase = ContactDatabase.getDatabase(this)

        // Save button click listener
        buttonSave.setOnClickListener {
            saveContact()
        }
    }

    private fun saveContact() {
        val name = editTextName.text.toString().trim()
        val phone = editTextPhone.text.toString().trim()
        val email = editTextEmail.text.toString().trim()

        if (name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()) {
            val contact = Contact(name = name, phone = phone, email = email)

            CoroutineScope(Dispatchers.IO).launch {
                contactDatabase.contactDao().insertContact(contact)
                runOnUiThread {
                    Toast.makeText(this@AddContactActivity, "Contact Saved!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        } else {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
        }
    }
}
