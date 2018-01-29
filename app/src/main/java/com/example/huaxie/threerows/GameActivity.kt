package com.example.huaxie.threerows

import android.animation.ObjectAnimator
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.example.huaxie.threerows.adapters.BoardCellAdapter
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.cover_splash.*


const val PLAYER_ONE = 1
const val PLAYER_TWO = 2
const val NO_PLAYER = 0
class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val numberOfColumns = 3
        recyclerViewGameBoard.layoutManager = GridLayoutManager(this, numberOfColumns)
        recyclerViewGameBoard.adapter = BoardCellAdapter.newInstance(this)
        startGame()
    }

    companion object {
        val playerOne = Player.newInstance(R.drawable.icon_star, PLAYER_ONE)
        val playerTwo = Player.newInstance(R.drawable.icon_circle, PLAYER_TWO)
        private const val ANIMATION_DURATION: Long = 1000
        const val ANIMATION_DELAY: Long = 1000
        var piecesPositons : ArrayList<Int> = arrayListOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
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

        fun checkWinner(player: Player): Boolean{
            if (ALL_WINNING_POSITIONS.contains(player.piecesPostions)) {
                return true;
            }
            return false
        }
    }

    private fun startGame() {
        enablePlayerOne()
    }

    fun endGame() {
        clearBoard()
        playerOne.apply {
            isEnabled = false
            piecesUsedOut = false
            remainingPieces = 3
        }
        playerTwo.apply {
            isEnabled = false
            piecesUsedOut = false
            remainingPieces = 3
        }
    }

    fun highLightPieces(player: Player) {
        player.piecesPostions
                .asSequence()
                .map { recyclerViewGameBoard.findViewHolderForAdapterPosition(it) as BoardCellAdapter.ViewHolder }
                .forEach { it.pieceImage.drawable.setColorFilter(ContextCompat.getColor(this,R.color.gold), PorterDuff.Mode.SRC_IN) }
    }

    fun removePiecesOutsideBoard(playerNumber: Int, remainingPieces: Int) {
        if (playerNumber == PLAYER_ONE) {
            when( 3 - remainingPieces) {
                1 -> playerOneImage1.setImageDrawable(null)
                2 -> playerOneImage2.setImageDrawable(null)
                3 -> playerOneImage3.setImageDrawable(null)
            }
        }
        if (playerNumber == PLAYER_TWO) {
            when( 3 - remainingPieces) {
                1 -> playerTwoImage3.setImageDrawable(null)
                2 -> playerTwoImage2.setImageDrawable(null)
                3 -> playerTwoImage1.setImageDrawable(null)
            }
        }
    }

    private fun clearBoard() {
        for (position in 1..recyclerViewGameBoard.adapter.itemCount){
            (recyclerViewGameBoard.findViewHolderForAdapterPosition(position - 1)
                    as BoardCellAdapter.ViewHolder).pieceImage.setImageDrawable(null)
        }
    }

    fun showCoverSplash(){
        coverSplashContainer?.let {
            it.visibility = View.VISIBLE
            coverSplashLayout?.apply {
                it.alpha = 0f
                startFadeInAnimator(it)
            }
        }
    }

    private fun startFadeInAnimator(view: View) {
        val fadeInAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        fadeInAnimator.duration = ANIMATION_DURATION
        fadeInAnimator.start()

    }

//    private fun startCircularRevealAnimation(view: View) {
//        val x = this.resources.displayMetrics.widthPixels / 2
//        val y = this.resources.displayMetrics.heightPixels / 2
//        AnimationUtil.showCircularReveal(x, y, view)
//    }

    override fun onBackPressed() {
        super.onBackPressed()
        //todo see if the game is ongoing, then need to show the dialog
        endGame()
    }
}
