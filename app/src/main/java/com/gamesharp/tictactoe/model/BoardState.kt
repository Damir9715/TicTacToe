package com.gamesharp.tictactoe.model

sealed class BoardState {
    object Incomplete : BoardState()
    data class CircleWon(val lineState: LineState) : BoardState()
    data class CrossWon(val lineState: LineState) : BoardState()
    object Draw : BoardState()
}