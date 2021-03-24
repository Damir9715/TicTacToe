package com.gamesharp.tictactoe.model

sealed class CellState {
    object Empty : CellState()
    object Circle : CellState()
    object Cross : CellState()
}