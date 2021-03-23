package com.gamesharp.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val gameState = MutableStateFlow<GameState>(GameState.Start)
    val gameStateText = MutableStateFlow("Start game 'X'")
    val isFigureClickable = MutableStateFlow(true)
    val currentPlayer: MutableStateFlow<Cell> = MutableStateFlow(Cell.Cross)

    val board = MutableStateFlow(
        listOf<Cell>(
            Cell.Empty, Cell.Empty, Cell.Empty,
            Cell.Empty, Cell.Empty, Cell.Empty,
            Cell.Empty, Cell.Empty, Cell.Empty,
        )
    )

    fun onClick(clickData: ClickData) {
        viewModelScope.launch {
            if (currentPlayer.value == Cell.Cross) {
                currentPlayer.emit(Cell.Circle)
            } else {
                currentPlayer.emit(Cell.Cross)
            }
            val temp = mutableListOf<Cell>()
            temp.addAll(board.value)
            temp[clickData.index] = clickData.cell
            board.emit(temp)
        }
    }

    init {
        viewModelScope.launch {
            gameState.collect {
                when (it) {
                    is GameState.Start -> {
//                        gameStateText.emit("Start game 'X'")
//                        isFigureClickable.emit(true)
                    }
                    is GameState.Game -> {
                        gameStateText.emit("X/O Turn")
                        isFigureClickable.emit(true)
                    }
                    is GameState.Ending -> {
                        gameStateText.emit("Game over")
                        isFigureClickable.emit(false)
                    }
                    is GameState.Result -> {
//                        gameStateText.emit("Game over")
//                        isFigureClickable.emit(false)
                    }
                }
            }
        }
    }
}

sealed class GameState {
    object Start : GameState()      //text, no-line, cells,             button unavailable
    object Game : GameState()       //text, no-line, cells,             button unavailable
    object Ending : GameState()     //text, line,    cells unavailable, button unavailable
    object Result : GameState()     //text, no-line, no-cells,          button available
}

sealed class Cell {
    object Empty : Cell()
    object Circle : Cell()
    object Cross : Cell()
}

data class ClickData(val index: Int, val cell: Cell)