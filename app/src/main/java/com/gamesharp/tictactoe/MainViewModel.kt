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
    val setLineColor: MutableStateFlow<CellState> = MutableStateFlow(CellState.Empty)
    val drawLine: MutableStateFlow<LineState> = MutableStateFlow(LineState.Empty)

    val gameStateText = MutableStateFlow("Start game 'X'")
    val currentPlayer: MutableStateFlow<CellState> = MutableStateFlow(CellState.Cross)
    val isResetClickable = MutableStateFlow(false)
    val isFigureClickable = MutableStateFlow(true)
    val isGameEnded = MutableStateFlow(false)

    private val emptyBoard = listOf<CellState>(
        CellState.Empty, CellState.Empty, CellState.Empty,
        CellState.Empty, CellState.Empty, CellState.Empty,
        CellState.Empty, CellState.Empty, CellState.Empty,
    )

    val board = MutableStateFlow(emptyBoard)

    fun onClick(clickData: ClickData) {
        viewModelScope.launch {
            gameState.emit(GameState.Game)

            if (currentPlayer.value == CellState.Cross) {
                currentPlayer.emit(CellState.Circle)
                gameStateText.emit("O Turn")
            } else {
                currentPlayer.emit(CellState.Cross)
                gameStateText.emit("X Turn")
            }
            val temp = mutableListOf<CellState>()
            temp.addAll(board.value)
            temp[clickData.index] = clickData.cellState
            board.emit(temp)

            when (val result = checkGameState(board.value)) {
                is GameCheckResult.Incomplete -> Unit
                is GameCheckResult.Circle -> {
                    setLineColor.emit(CellState.Circle)
                    drawLine.emit(result.state)

                    gameState.emit(GameState.Ending)
                    delay(ENDING_DELAY)
                    gameState.emit(GameState.Result)
                }
                is GameCheckResult.Cross -> {
                    setLineColor.emit(CellState.Cross)
                    drawLine.emit(result.state)

                    gameState.emit(GameState.Ending)
                    delay(ENDING_DELAY)
                    gameState.emit(GameState.Result)
                }
                is GameCheckResult.Draw -> {
                    setLineColor.emit(CellState.Empty)

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
                        currentPlayer.emit(CellState.Cross)
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
    object Start : GameState()
    object Game : GameState()
    object Ending : GameState()
    object Result : GameState()
}

sealed class CellState {
    object Empty : CellState()
    object Circle : CellState()
    object Cross : CellState()
}

data class ClickData(val index: Int, val cellState: CellState)