package com.example.huaxie.threerows

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.example.huaxie.threerows.adapters.BoardCellAdapter
import kotlinx.android.synthetic.main.activity_game.*


const val PLAYER_ONE = 1
const val PLAYER_TWO = 2
const val NO_PLAYER = 0
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
        var playerOnePositions : ArrayList<Int> = arrayListOf(-1, -1, -1)
        var playerTwoPositions : ArrayList<Int> = arrayListOf(-1, -1, -1)
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

        private val WINNING_POSITION_1 = hashSetOf(0, 1, 2)
        private val WINNING_POSITION_2 = hashSetOf(3, 4, 5)
        private val WINNING_POSITION_3 = hashSetOf(6, 7, 8)
        private val WINNING_POSITION_4 = hashSetOf(0, 3, 6)
        private val WINNING_POSITION_5 = hashSetOf(1, 4, 7)
        private val WINNING_POSITION_6 = hashSetOf(2, 5, 8)
        private val WINNING_POSITION_7 = hashSetOf(0, 4, 8)
        private val WINNING_POSITION_8 = hashSetOf(2, 4, 6)
        private val ALL_WINNING_POSITIONS = hashSetOf<Set<Int>>(WINNING_POSITION_1,
                WINNING_POSITION_2, WINNING_POSITION_3,
                WINNING_POSITION_4, WINNING_POSITION_5,
                WINNING_POSITION_6, WINNING_POSITION_7,
                WINNING_POSITION_8)


        fun checkWinner(isPlayerOne: Boolean) : Boolean{
            val playerPositions : Set<Int> = getPlayerPositions(isPlayerOne)
            if (ALL_WINNING_POSITIONS.contains(playerPositions)) {
                return true;
            }
            return false
        }

        private fun getPlayerPositions(isPlayerOne : Boolean): Set<Int> {
            val positions : MutableSet<Int> = LinkedHashSet()
            piecesPositons.forEachIndexed({index, value -> when (Pair(value, isPlayerOne)) {
                Pair(PLAYER_ONE, true) -> {
                    positions.add(index)
                }
                Pair(PLAYER_TWO, false) -> {
                    positions.add(index)
                }
            }})

            return positions
        }
    }

    private fun startGame() {
        enablePlayerOne()
    }

}
