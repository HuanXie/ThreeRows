package com.example.huaxie.threerows

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        headIconPeppaPig.setOnClickListener {
            startGameActivity()
        }
    }

    private fun startGameActivity() {
        val intent = Intent(
                this,GameActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent
//                .FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}
