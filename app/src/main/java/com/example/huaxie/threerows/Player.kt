package com.example.huaxie.threerows

class Player(val imageId: Int) {

    companion object {
        var piecesUsedOut: Boolean = false
        var remainingPieces: Int = 3

        fun newInstance(imageId: Int): Player {
            return Player(imageId)
        }
    }
}