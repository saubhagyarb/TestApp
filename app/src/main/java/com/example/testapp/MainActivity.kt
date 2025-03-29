package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil3.load
//import com.dotlottie.dlplayer.Mode
import com.google.android.material.button.MaterialButton
//import com.lottiefiles.dotlottie.core.util.DotLottieSource
//import com.lottiefiles.dotlottie.core.widget.DotLottieAnimation

class MainActivity : AppCompatActivity() {
    lateinit var getStartedBtn : MaterialButton
//    lateinit var backgroundImage : DotLottieAnimation

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

//        backgroundImage = findViewById(R.id.animationImage)
//        val config = DotLottieConfig.Builder()
//            .autoplay(true)
//            .speed(1f)
//            .loop(true)
//            .source(DotLottieSource.Url("<https://lottie.host/294b684d-d6b4-4116-ab35-85ef566d4379/VkGHcqcMUI.lotti>")) // URL of .json or .lottie
//            .useInterpolation(true)
//            .playMode(Mode.FORWARD)
//            .build()
//        backgroundImage.load(config)


    }
}