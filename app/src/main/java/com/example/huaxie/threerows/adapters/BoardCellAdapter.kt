package com.example.huaxie.threerows.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
//            pieceImage.setImageDrawable(context.getDrawable(R.drawable.icon_star))
            setOnClickListener({
//                pieceImage.setImageDrawable(context.getDrawable(R.drawable.icon_smile))
            })
        }
    }

}