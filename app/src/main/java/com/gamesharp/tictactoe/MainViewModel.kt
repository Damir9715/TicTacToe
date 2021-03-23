package com.gamesharp.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val ENDING_DELAY = 1000L

class MainViewModel : ViewModel() {
    private val gameState = MutableStateFlow<GameState>(GameState.Start)
    val setLineColor: MutableStateFlow<Cell> = MutableStateFlow(Cell.Empty)
    val drawLine: MutableStateFlow<LineState> = MutableStateFlow(LineState.Empty)

    val gameStateText = MutableStateFlow("Start game 'X'")
    val currentPlayer: MutableStateFlow<Cell> = MutableStateFlow(Cell.Cross)
    val isResetClickable = MutableStateFlow(false)
    val isFigureClickable = MutableStateFlow(true)
    val isGameEnded = MutableStateFlow(false)

    private val emptyBoard = listOf<Cell>(
        Cell.Empty, Cell.Empty, Cell.Empty,
        Cell.Empty, Cell.Empty, Cell.Empty,
        Cell.Empty, Cell.Empty, Cell.Empty,
    )

    val board = MutableStateFlow(emptyBoard)

    fun onClick(clickData: ClickData) {
        viewModelScope.launch {
            gameState.emit(GameState.Game)

            if (currentPlayer.value == Cell.Cross) {
                currentPlayer.emit(Cell.Circle)
                gameStateText.emit("O Turn")
            } else {
                currentPlayer.emit(Cell.Cross)
                gameStateText.emit("X Turn")
            }
            val temp = mutableListOf<Cell>()
            temp.addAll(board.value)
            temp[clickData.index] = clickData.cell
            board.emit(temp)

            when (val result = checkGameState(board.value)) {
                is GameCheckResult.Continue -> Unit
                is GameCheckResult.Circle -> {
                    setLineColor.emit(Cell.Circle)
                    drawLine.emit(result.state)

                    gameState.emit(GameState.Ending)
                    delay(ENDING_DELAY)
                    gameState.emit(GameState.Result)
                }
                is GameCheckResult.Cross -> {
                    setLineColor.emit(Cell.Cross)
                    drawLine.emit(result.state)

                    gameState.emit(GameState.Ending)
                    delay(ENDING_DELAY)
                    gameState.emit(GameState.Result)
                }
                is GameCheckResult.Draw -> {
                    setLineColor.emit(Cell.Empty)

                    gameState.emit(GameState.Ending)
                    delay(ENDING_DELAY)
                    gameState.emit(GameState.Result)
                }
            }
        }
    }

    fun onReset() {
        viewModelScope.launch {
            gameState.emit(GameState.Start)
        }
    }

    init {
        viewModelScope.launch {
            gameState.collect {
                when (it) {
                    is GameState.Start -> {
                        board.emit(emptyBoard)
                        drawLine.emit(LineState.Empty)
                        gameStateText.emit("X Turn")
                        isGameEnded.emit(false)
                        isFigureClickable.emit(true)
                        isResetClickable.emit(false)
                        currentPlayer.emit(Cell.Cross)
                    }
                    is GameState.Game -> {
                        isResetClickable.emit(true)
                    }
                    is GameState.Ending -> {
                        //fixme remove, come up with better ux
                        isResetClickable.emit(false)
                        gameStateText.emit("Game over")
                        isFigureClickable.emit(false)
                    }
                    is GameState.Result -> {
                        //fixme remove, come up with better ux
                        isResetClickable.emit(true)
                        isGameEnded.emit(true)
                    }
                }
            }
        }
    }
}

sealed class GameState {
    object Start : GameState()      //no-line, cells,
    object Game : GameState()       //no-line, cells,
    object Ending : GameState()     //line,    cells unavailable
    object Result : GameState()     //no-line, no-cells,
}

sealed class Cell {
    object Empty : Cell()
    object Circle : Cell()
    object Cross : Cell()
}

data class ClickData(val index: Int, val cell: Cell)