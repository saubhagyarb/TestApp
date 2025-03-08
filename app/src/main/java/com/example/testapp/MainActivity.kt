package com.example.testapp

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val wick = findViewById<ImageView>(R.id.wick)
        val ladybug = findViewById<ImageView>(R.id.ladybug)
        val openheimer = findViewById<ImageView>(R.id.openheimer)
        val clonedone = findViewById<ImageView>(R.id.clonedone)
        val barbie = findViewById<ImageView>(R.id.barbie)
        val marmaid = findViewById<ImageView>(R.id.marmaid)
        val futurama = findViewById<ImageView>(R.id.futurama)

        wick.animation = AnimationUtils.loadAnimation(this, R.anim.shake_animation)
        ladybug.animation = AnimationUtils.loadAnimation(this, R.anim.shake_animation)
        openheimer.animation = AnimationUtils.loadAnimation(this, R.anim.shake_animation)
        clonedone.animation = AnimationUtils.loadAnimation(this, R.anim.shake_animation)
        barbie.animation = AnimationUtils.loadAnimation(this, R.anim.shake_animation)
        marmaid.animation = AnimationUtils.loadAnimation(this, R.anim.shake_animation)
        futurama.animation = AnimationUtils.loadAnimation(this, R.anim.shake_animation)

    }
}