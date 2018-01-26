package com.example.huaxie.threerows

class Player private constructor(imageId: Int) {
    var piecesUsedOut: Boolean = false
    var remainingPieces: Int = 3
    var isEnabled: Boolean = false
    var piecesPostions: MutableSet<Int> = HashSet()

    companion object {

        fun newInstance(imageId: Int): Player {
            return Player(imageId)
        }

    }

}