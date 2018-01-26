package com.example.huaxie.threerows.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.huaxie.threerows.GameActivity
import com.example.huaxie.threerows.NO_PLAYER
import com.example.huaxie.threerows.PLAYER_ONE
import com.example.huaxie.threerows.PLAYER_TWO
import com.example.huaxie.threerows.R

class BoardCellAdapter : RecyclerView.Adapter<BoardCellAdapter.ViewHolder>() {

    companion object {
        private val positions: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

        fun newInstance() : BoardCellAdapter = BoardCellAdapter()
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
            setOnClickListener(MyFirstThreeStepsOnclickListener(pieceImage, context, position))
        }
    }

    class MyFirstThreeStepsOnclickListener(val pieceImage: ImageView, val context: Context, val position: Int) : View.OnClickListener {

        override fun onClick(v: View?) {
            if (!GameActivity.hasPiecesUsedOut()) {  // either player has some unused pieces
                if (pieceImage.drawable != null) {
                    return
                }
                if (GameActivity.isPlayOneEnabled() && GameActivity.playerOne.remainingPieces > 0 ) {
                    pieceImage.setImageDrawable(context.getDrawable(R.drawable.icon_star))
                    GameActivity.piecesPositons[position] = PLAYER_ONE
                    GameActivity.playerOne.remainingPieces--
                    if (GameActivity.playerOne.remainingPieces == 0) {
                        GameActivity.playerOne.piecesUsedOut = true
                        if (GameActivity.checkWinner(isPlayerOne = true)) {
                            Toast.makeText(context, "Play ONE WIN!!!", Toast.LENGTH_SHORT).show()
//                            endGame()
                        }
                    }
                    GameActivity.enablePlayerTwo()
                    GameActivity.disablePlayerOne()
                    return
                } else if (GameActivity.isPlayTwoEnabled() && GameActivity.playerTwo.remainingPieces > 0) {
                    pieceImage.setImageDrawable(context.getDrawable(R.drawable.icon_smile))
                    GameActivity.playerTwo.remainingPieces--
                    GameActivity.piecesPositons[position] = PLAYER_TWO
                    if (GameActivity.playerTwo.remainingPieces == 0) {
                        GameActivity.playerTwo.piecesUsedOut = true
                        if (GameActivity.checkWinner(isPlayerOne = false)) {
                            Toast.makeText(context, "Play TWO WIN!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    GameActivity.enablePlayerOne()
                    GameActivity.disablePlayerTwo()
                    return
                }
            } else {   // all the pieces are on the board, click event only remove the Drawable
                if (GameActivity.playerOne.isEnabled && pieceImage.drawable != null
                        && GameActivity.piecesPositons[position] == PLAYER_ONE ){
                    pieceImage.setImageDrawable(null)
                    GameActivity.piecesPositons[position] = NO_PLAYER
                    GameActivity.playerOne.piecesUsedOut = false
                    GameActivity.playerOne.remainingPieces++
                    return
                }

                if (GameActivity.playerTwo.isEnabled && pieceImage.drawable != null
                        && GameActivity.piecesPositons[position] == PLAYER_TWO ){
                    pieceImage.setImageDrawable(null)
                    GameActivity.playerTwo.piecesUsedOut = false
                    GameActivity.piecesPositons[position] = NO_PLAYER
                    GameActivity.playerTwo.remainingPieces++
                    return
                }

            }
        }

    }

}