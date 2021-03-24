package com.gamesharp.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamesharp.tictactoe.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val ENDING_DELAY = 1000L

class MainViewModel : ViewModel() {

    val board = Board(scope = viewModelScope)

    private val gameState = MutableStateFlow<GameState>(GameState.Start)
    val setLineColor: MutableStateFlow<CellState> = MutableStateFlow(CellState.Empty)
    val drawLine: MutableStateFlow<LineState> = MutableStateFlow(LineState.Empty)

    val gameStateText = MutableStateFlow("Start game 'X'")
    val currentPlayer: MutableStateFlow<CellState> = MutableStateFlow(CellState.Cross)
    //todo bug
    val isResetClickable = MutableStateFlow(false)
    val isFigureClickable = MutableStateFlow(true)
    val isGameEnded = MutableStateFlow(false)

    fun onClick(index: Int, cellState: CellState) {
        viewModelScope.launch {
            updateCurrentPlayer()
            setCell(index, cellState)
        }
    }

    private fun setCell(index: Int, cellState: CellState) {
        viewModelScope.launch {
            val temp = mutableListOf<CellState>()
            temp.addAll(board.cells.value)
            temp[index] = cellState
            board.cells.emit(temp)
        }
    }

    private fun updateCurrentPlayer() {
        viewModelScope.launch {
            if (currentPlayer.value == CellState.Cross) {
                currentPlayer.emit(CellState.Circle)
                gameStateText.emit("O Turn")
            } else {
                currentPlayer.emit(CellState.Cross)
                gameStateText.emit("X Turn")
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
                        board.resetBoard()
                        drawLine.emit(LineState.Empty)
                        gameStateText.emit("X Turn")
                        isGameEnded.emit(false)
                        isFigureClickable.emit(true)
                        isResetClickable.emit(true)
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

        viewModelScope.launch {
            board.boardState.collect { boardState ->
                when (boardState) {
                    is BoardState.Incomplete -> gameState.emit(GameState.Game)
                    is BoardState.CircleWon -> {
                        setLineColor.emit(CellState.Circle)
                        drawLine.emit(boardState.state)

                        gameState.emit(GameState.Ending)
                        delay(ENDING_DELAY)
                        gameState.emit(GameState.Result)
                    }
                    is BoardState.CrossWon -> {
                        setLineColor.emit(CellState.Cross)
                        drawLine.emit(boardState.state)

                        gameState.emit(GameState.Ending)
                        delay(ENDING_DELAY)
                        gameState.emit(GameState.Result)
                    }
                    is BoardState.Draw -> {
                        setLineColor.emit(CellState.Empty)

                        gameState.emit(GameState.Ending)
                        delay(ENDING_DELAY)
                        gameState.emit(GameState.Result)
                    }
                }
            }
        }
    }
}