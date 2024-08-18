package com.fifteen11.jetpacksamplelibrary.quotes.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.fifteen11.jetpacksamplelibrary.R

class AddNotesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val title = findViewById<EditText>(R.id.txt_title)
        val description = findViewById<EditText>(R.id.txt_description)
        val btnSubmit = findViewById<Button>(R.id.btn_submit)

        btnSubmit.setOnClickListener {
            val titleText = title.text.toString()
            val descriptionText = description.text.toString()

            val msg = "Title - $titleText | Description - $descriptionText"
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("KEY", msg)
            startActivity(intent)
        }
    }
}