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
    private val board: Board = Board.Default(scope = viewModelScope)

    private val showOutcome = MutableSharedFlow<Unit>()

    val cells = MutableStateFlow(emptyList<CellState>())

    val isOutcomeVisible = MutableStateFlow(false)

    val boardState: MutableStateFlow<BoardState> = MutableStateFlow(BoardState.Incomplete)

    val drawLine: MutableStateFlow<LineState> = MutableStateFlow(LineState.Empty)

    val gameStateText = MutableStateFlow("Start game 'X'")

    val currentPlayer: MutableStateFlow<CellState> = MutableStateFlow(CellState.Cross)

    val reset = MutableSharedFlow<Unit>()

    val click = MutableSharedFlow<Pair<Int, CellState>>()

    init {
        viewModelScope.launch {
            board.cells.collect {
                cells.emit(it)
            }
        }

        viewModelScope.launch {
            board.state.collect { state ->
                boardState.emit(state)

                when (state) {
                    is BoardState.Incomplete -> {
                        isOutcomeVisible.emit(false)
                        drawLine.emit(LineState.Empty)
                        gameStateText.emit("X Turn")
                        currentPlayer.emit(CellState.Cross)
                    }
                    is BoardState.CircleWon -> {
                        gameStateText.emit("Game over")
                        drawLine.emit(state.lineState)
                        showOutcome.emit(Unit)
                    }
                    is BoardState.CrossWon -> {
                        gameStateText.emit("Game over")
                        drawLine.emit(state.lineState)
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
            click.collect { (index, cellState) ->
                board.addItem.emit(index to cellState)

                if (currentPlayer.value == CellState.Cross) {
                    currentPlayer.emit(CellState.Circle)
                    gameStateText.emit("O Turn")
                } else {
                    currentPlayer.emit(CellState.Cross)
                    gameStateText.emit("X Turn")
                }
            }
        }

        viewModelScope.launch {
            showOutcome.collect {
                delay(1000)
                isOutcomeVisible.emit(true)
            }
        }

        viewModelScope.launch {
            reset.collect {
                board.clear.emit(Unit)
            }
        }
    }
}