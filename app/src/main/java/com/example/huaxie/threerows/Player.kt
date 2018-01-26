package com.example.huaxie.threerows

class Player private constructor(imageId: Int) {
    var piecesUsedOut: Boolean = false
    var remainingPieces: Int = 3
    var isEnabled: Boolean = false

    companion object {

        fun newInstance(imageId: Int): Player {
            return Player(imageId)
        }
    }

}