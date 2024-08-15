package com.fifteen11.jetpacksamplelibrary.quotestesting.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.fifteen11.jetpacksamplelibrary.R

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val msg = intent.getStringExtra("msg")
        val textView = findViewById<AppCompatTextView>(R.id.txtMessage)

        textView.text = msg
    }
}