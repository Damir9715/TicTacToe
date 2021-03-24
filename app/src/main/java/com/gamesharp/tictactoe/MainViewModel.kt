package com.gamesharp.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamesharp.tictactoe.model.Board
import com.gamesharp.tictactoe.model.BoardState
import com.gamesharp.tictactoe.model.CellState
import com.gamesharp.tictactoe.model.LineState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val board = Board(scope = viewModelScope)

    val drawLine: MutableStateFlow<LineState> = MutableStateFlow(LineState.Empty)

    val gameStateText = MutableStateFlow("Start game 'X'")

    val currentPlayer: MutableStateFlow<CellState> = MutableStateFlow(CellState.Cross)

    val isOutcomeVisible = MutableStateFlow(false)

    private val showOutcome = MutableSharedFlow<Unit>()

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
            board.resetBoard()
        }
    }

    init {
        viewModelScope.launch {
            board.boardState.collect { boardState ->
                when (boardState) {
                    is BoardState.Incomplete -> {
                        isOutcomeVisible.emit(false)
                        drawLine.emit(LineState.Empty)
                        gameStateText.emit("X Turn")
                        currentPlayer.emit(CellState.Cross)
                    }
                    is BoardState.CircleWon -> {
                        gameStateText.emit("Game over")
                        drawLine.emit(boardState.lineState)
                        showOutcome.emit(Unit)
                    }
                    is BoardState.CrossWon -> {
                        gameStateText.emit("Game over")
                        drawLine.emit(boardState.lineState)
                        showOutcome.emit(Unit)
                    }
                    is BoardState.Draw -> {
                        gameStateText.emit("Game over")
                        showOutcome.emit(Unit)
                    }
                }
            }
        }

        viewModelScope.launch {
            showOutcome.collect {
                delay(1000)
                isOutcomeVisible.emit(true)
            }
        }
    }
}