package com.example.huaxie.threerows

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton.setOnClickListener {
            startGameActivity()
        }
    }

    private fun startGameActivity() {
        val intent = Intent(
                this,GameActivity::class.java)
        startActivity(intent)
    }

}
