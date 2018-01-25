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
            if (GameActivity.piecesUsedOut){
                if (GameActivity.isPlayOneEnabled() && pieceImage.drawable != null
                        && GameActivity.piecesPositons[position] == PLAYER_ONE ){
                    pieceImage.setImageDrawable(null)
                    GameActivity.piecesUsedOut = false
                    GameActivity.remainingPiecesForPlayOne++
                    return
                }

                if (!GameActivity.isPlayOneEnabled() && pieceImage.drawable != null
                        && GameActivity.piecesPositons[position] == PLAYER_TWO ){
                    pieceImage.setImageDrawable(null)
                    GameActivity.piecesUsedOut = false
                    GameActivity.remainingPiecesForPlayTwo++
                    return
                }
            }
            if (pieceImage.drawable == null && !GameActivity.piecesUsedOut) {
                if (GameActivity.isPlayOneEnabled() && GameActivity.remainingPiecesForPlayOne > 0 ) {
                    pieceImage.setImageDrawable(context.getDrawable(R.drawable.icon_star))
                    GameActivity.piecesPositons[position] = PLAYER_ONE
                    GameActivity.remainingPiecesForPlayOne--
                    GameActivity.enablePlayerTwo()
                    return
                } else {
                    if (GameActivity.remainingPiecesForPlayTwo > 0) {
                        pieceImage.setImageDrawable(context.getDrawable(R.drawable.icon_smile))
                        GameActivity.remainingPiecesForPlayTwo--
                        GameActivity.piecesPositons[position] = PLAYER_TWO
                        if (GameActivity.remainingPiecesForPlayTwo == 0) {
                            GameActivity.piecesUsedOut = true
                            Toast.makeText(context,"请选中你想要移动的棋子，再移动到下一个位置", Toast.LENGTH_SHORT).show()
                        }
                        GameActivity.enablePlayerOne()
                    }
                    return
                }
            } else {
                Toast.makeText(context,"piece can not overlap each other", Toast.LENGTH_SHORT).show()
            }

        }

    }

}