package com.example.huaxie.threerows.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.huaxie.threerows.GameActivity
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
        companion object {
            private const val PLAYER_ONE = 1
            private const val PLAYER_TWO = 2
        }
        override fun onClick(v: View?) {
            if (!GameActivity.hasPiecesUsedOut()) {  // either player has some unused pieces
                if (pieceImage.drawable != null) {
                    Toast.makeText(context,"piece can not overlap each other", Toast.LENGTH_SHORT).show()
                    return
                }
                if (GameActivity.isPlayOneEnabled() && GameActivity.playerOne.remainingPieces > 0 ) {
                    pieceImage.setImageDrawable(context.getDrawable(R.drawable.icon_star))
                    GameActivity.piecesPositons[position] = PLAYER_ONE
                    GameActivity.playerOne.remainingPieces--
                    if (GameActivity.playerOne.remainingPieces == 0) {
                        GameActivity.playerOne.piecesUsedOut = true
                        Toast.makeText(context,"Player One 的棋子用完了, 下一轮请选中你想要移动的棋子，再移动到下一个位置", Toast.LENGTH_SHORT).show()
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
                        Toast.makeText(context,"Player Two 的棋子用完了，请选中你想要移动的棋子，再移动到下一个位置", Toast.LENGTH_SHORT).show()
                    }
                    GameActivity.enablePlayerOne()
                    GameActivity.disablePlayerTwo()
                    return
                }
            } else {   // all the pieces are on the board, click event only remove the Drawable
                if (GameActivity.isPlayOneEnabled() && pieceImage.drawable != null
                        && GameActivity.piecesPositons[position] == PLAYER_ONE ){
                    pieceImage.setImageDrawable(null)
                    GameActivity.playerOne.piecesUsedOut = false
                    GameActivity.playerOne.remainingPieces++
                    return
                }

                if (GameActivity.isPlayTwoEnabled() && pieceImage.drawable != null
                        && GameActivity.piecesPositons[position] == PLAYER_TWO ){
                    pieceImage.setImageDrawable(null)
                    GameActivity.playerTwo.piecesUsedOut = false
                    GameActivity.playerTwo.remainingPieces++
                    return
                }

            }
        }

    }

}