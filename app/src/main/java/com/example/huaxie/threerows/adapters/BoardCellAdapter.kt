package com.example.huaxie.threerows.adapters

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.huaxie.threerows.GameActivity
import com.example.huaxie.threerows.NO_PLAYER
import com.example.huaxie.threerows.Player
import com.example.huaxie.threerows.R

class BoardCellAdapter(val activity: GameActivity) : RecyclerView.Adapter<BoardCellAdapter.ViewHolder>() {

    companion object {
        private val positions: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        private val mMainThreadHandler = Handler(Looper.getMainLooper())
        fun newInstance(gameActivity: GameActivity): BoardCellAdapter = BoardCellAdapter(gameActivity)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.board_cell_view, parent, false))

    override fun getItemCount(): Int = positions.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(position)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val pieceImage: ImageView = itemView.findViewById(R.id.pieceHolder)
        fun bind(position: Int) = with(itemView) {
            setOnClickListener(MyOnclickListener(pieceImage, context, position))
        }
    }

    inner class MyOnclickListener(val pieceImage: ImageView, val context: Context, val position: Int) : View.OnClickListener {

        override fun onClick(v: View?) {
            if (!GameActivity.hasPiecesUsedOut()) {  // either player has some unused pieces
                if (pieceImage.drawable != null) {
                    return
                }
                GameActivity.playerOne.apply {
                    if (isEnabled && remainingPieces > 0) {
                        pieceAction()
                        GameActivity.enablePlayerTwo()
                        GameActivity.disablePlayerOne()
                        return
                    }
                }
                GameActivity.playerTwo.apply {
                    if (isEnabled && remainingPieces > 0) {
                        pieceAction()
                        GameActivity.enablePlayerOne()
                        GameActivity.disablePlayerTwo()
                        return
                    }
                }
            } else {   // all the pieces are on the board, click event only remove the Drawable
                GameActivity.playerOne.apply {
                    if (isEnabled && pieceImage.drawable != null
                            && GameActivity.piecesPositons[position] == playerId ){
                        pieceImage.setImageDrawable(null)
                        GameActivity.piecesPositons[position] = NO_PLAYER
                        piecesPostions.remove(position)
                        piecesUsedOut = false
                        remainingPieces++
                        return
                    }
                }

                GameActivity.playerTwo.apply {
                    if (isEnabled && pieceImage.drawable != null
                            && GameActivity.piecesPositons[position] == playerId ){
                        pieceImage.setImageDrawable(null)
                        piecesUsedOut = false
                        GameActivity.piecesPositons[position] = NO_PLAYER
                        piecesPostions.remove(position)
                        remainingPieces++
                        return
                    }
                }
            }
        }

        private fun Player.pieceAction() {
            pieceImage.setImageDrawable(context.getDrawable(imageId))
            GameActivity.piecesPositons[position] = playerId
            piecesPostions.add(position)
            remainingPieces--
            activity.removePiecesOutsideBoard(playerId, remainingPieces)
            if (remainingPieces == 0) {
                piecesUsedOut = true
                if (GameActivity.checkWinner(this)) {
                    lightOnImageForWinner(this)
                    scores++
                    mMainThreadHandler.postDelayed({
                        activity.updateScores()
                        activity.showCoverSplash()
                        activity.endGame()
                    }, GameActivity.ANIMATION_DELAY)
                }
            }
        }

        private fun lightOnImageForWinner(player: Player) {
            activity.highLightPieces(player)
        }
    }

}