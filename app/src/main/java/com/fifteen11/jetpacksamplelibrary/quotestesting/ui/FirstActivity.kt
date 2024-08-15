package com.fifteen11.jetpacksamplelibrary.quotestesting.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.fifteen11.jetpacksamplelibrary.R

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val edtTitle = findViewById<AppCompatEditText>(R.id.edtTitle)
        val edtDescription = findViewById<AppCompatEditText>(R.id.edtDescription)
        val btnSubmit = findViewById<AppCompatButton>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val title = edtTitle.text.toString()
            val description = edtDescription.text.toString()

            val msg = "Title is $title and Description is $description"
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("msg", msg)
            startActivity(intent)
        }
    }
}