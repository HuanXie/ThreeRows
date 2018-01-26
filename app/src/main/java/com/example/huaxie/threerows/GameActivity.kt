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
        val playerOne = Player.newInstance(R.drawable.icon_star)
        val playerTwo = Player.newInstance(R.drawable.icon_smile)
        var piecesPositons : ArrayList<Int> = arrayListOf(0, 0, 0, 0, 0, 0, 0, 0, 0)

        fun isPlayOneEnabled() : Boolean {
            return playerOne.isEnabled
        }

        fun isPlayTwoEnabled() : Boolean {
            return playerTwo.isEnabled
        }

        fun hasPiecesUsedOut() : Boolean{
            return playerOne.piecesUsedOut && playerTwo.piecesUsedOut
        }

        fun enablePlayerOne() {
            playerOne.isEnabled = true
            //todo set background of play1 to enable, disable background of play2
        }

        fun enablePlayerTwo() {
            playerTwo.isEnabled = true
            //todo set background of play2 to enable, disable background of play1
        }

        fun disablePlayerOne() {
            playerOne.isEnabled = false
            //todo set background of play1 to enable, disable background of play2
        }

        fun disablePlayerTwo() {
            playerTwo.isEnabled = false
            //todo set background of play1 to enable, disable background of play2
        }
    }

    private fun startGame() {
        enablePlayerOne()
    }

}
