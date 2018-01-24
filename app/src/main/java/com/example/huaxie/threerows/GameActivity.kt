package com.example.huaxie.threerows

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.example.huaxie.threerows.adapters.BoardCellAdapter
import kotlinx.android.synthetic.main.activity_game.*



class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val numberOfColumns = 3
        recyclerViewGameBoard.layoutManager = GridLayoutManager(this, numberOfColumns)
        recyclerViewGameBoard.adapter = BoardCellAdapter.newInstance()
    }
}
