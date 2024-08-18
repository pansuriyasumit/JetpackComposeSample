package com.fifteen11.jetpacksamplelibrary.quotes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.fifteen11.jetpacksamplelibrary.R

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val txtMessage = findViewById<AppCompatTextView>(R.id.txt_message)
        val msg = intent.getStringExtra("KEY")
        txtMessage.text = msg
    }
}