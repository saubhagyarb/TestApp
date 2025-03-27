package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil3.load
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    lateinit var getStartedBtn : MaterialButton
    lateinit var backgroundImage : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getStartedBtn = findViewById(R.id.getStartedBtn)


        getStartedBtn.setOnClickListener {
            startActivity(Intent(this, HomeScreen::class.java))
            finish()
        }

        backgroundImage = findViewById(R.id.backgroundImage)
        backgroundImage.load(R.drawable.backgroundimage)

    }
}