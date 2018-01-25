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
        startGame()
    }

    companion object {
        private var playerSwitcher: Boolean = false
        var piecesUsedOut: Boolean = false
        var remainingPiecesForPlayOne: Int = 3
        var remainingPiecesForPlayTwo: Int = 3
        var piecesPositons : ArrayList<Int> = arrayListOf(0, 0, 0, 0, 0, 0, 0, 0, 0)

        fun isPlayOneEnabled() : Boolean {
            return playerSwitcher
        }

        fun enablePlayerOne() {
            playerSwitcher = true
            //todo set background of play1 to enable, disable background of play2
        }

        fun enablePlayerTwo() {
            playerSwitcher = false
            //todo set background of play2 to enable, disable background of play1
        }
    }

    private fun startGame() {
        enablePlayerOne()
    }







}
