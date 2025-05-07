package com.example.android_wisata_indonesia_1.ui.addwisata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_wisata_indonesia_1.R

class AddWisataActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextDeskripsi: EditText
    private lateinit var editTextLocation: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_wisata)

        editTextName = findViewById(R.id.editTextName)
        editTextLocation = findViewById(R.id.editTextLocation)
        editTextDeskripsi = findViewById(R.id.editTextDeskripsi)
        btnSimpan = findViewById(R.id.buttonSimpan)

        btnSimpan.setOnClickListener {
            simpanWisata()
        }
    }

    private fun simpanWisata() {
        val name = editTextName.text.toString()
        val location = editTextLocation.text.toString()
        val description = editTextDeskripsi.text.toString()

        if (name.isBlank() || description.isBlank() || location.isBlank()) {
            Toast.makeText(this, "Name, Location & Description is required.", Toast.LENGTH_SHORT).show()
            return
        }

        val resultIntent = Intent().apply {
            putExtra("name", name)
            putExtra("location", location)
            putExtra("description", description)
        }

        setResult(RESULT_OK, resultIntent)
        finish()
    }
}