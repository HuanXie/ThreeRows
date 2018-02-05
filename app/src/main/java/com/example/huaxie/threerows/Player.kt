package com.example.huaxie.threerows

class Player private constructor(val imageId: Int, val playerId: Int) {
    var piecesUsedOut: Boolean = false
    var remainingPieces: Int = 3
    var isEnabled: Boolean = false
    var piecesPostions: MutableSet<Int> = HashSet()
    var scores: Int = 0

    companion object {
        fun newInstance(imageId: Int, playerId: Int): Player {
            return Player(imageId, playerId)
        }
    }

}