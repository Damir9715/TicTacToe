package com.gamesharp.tictactoe.model

sealed class BoardState {
    data class Incomplete(val state: LineState) : BoardState()
    data class CircleWon(val state: LineState) : BoardState()
    data class CrossWon(val state: LineState) : BoardState()
    data class Draw(val state: LineState) : BoardState()
}