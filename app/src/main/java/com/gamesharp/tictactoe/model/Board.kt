package com.gamesharp.tictactoe.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private val emptyBoard = listOf<CellState>(
    CellState.Empty, CellState.Empty, CellState.Empty,
    CellState.Empty, CellState.Empty, CellState.Empty,
    CellState.Empty, CellState.Empty, CellState.Empty,
)

class Board(
    val cells: MutableStateFlow<List<CellState>> = MutableStateFlow(emptyBoard),
    private val scope: CoroutineScope
) {

    val boardState: MutableStateFlow<BoardState> =
        MutableStateFlow(BoardState.Incomplete)

    init {
        scope.launch {
            cells.collect { cells ->
                boardState.emit(checkBoardState(cells))
            }
        }
    }

    private fun checkBoardState(cells: List<CellState>): BoardState {
        if (cells.filterNot { it == CellState.Empty }.count() < 5) {
            return BoardState.Incomplete
        }

        //rows
        if (cells[0] == cells[1] && cells[1] == cells[2] && cells[0] != CellState.Empty) {
            return toResult(cells[0], LineState.Row1)
        }
        if (cells[3] == cells[4] && cells[4] == cells[5] && cells[3] != CellState.Empty) {
            return toResult(cells[3], LineState.Row2)
        }
        if (cells[6] == cells[7] && cells[7] == cells[8] && cells[6] != CellState.Empty) {
            return toResult(cells[6], LineState.Row3)
        }

        //columns
        if (cells[0] == cells[3] && cells[3] == cells[6] && cells[0] != CellState.Empty) {
            return toResult(cells[0], LineState.Column1)
        }
        if (cells[1] == cells[4] && cells[4] == cells[7] && cells[1] != CellState.Empty) {
            return toResult(cells[1], LineState.Column2)
        }
        if (cells[2] == cells[5] && cells[5] == cells[8] && cells[2] != CellState.Empty) {
            return toResult(cells[2], LineState.Column3)
        }

        //cross
        if (cells[0] == cells[4] && cells[4] == cells[8] && cells[0] != CellState.Empty) {
            return toResult(cells[0], LineState.Cross1)
        }
        if (cells[2] == cells[4] && cells[4] == cells[6] && cells[2] != CellState.Empty) {
            return toResult(cells[2], LineState.Cross2)
        }

        //end
        return if (cells.filter { it == CellState.Empty }.count() == 0) {
            BoardState.Draw
        } else {
            BoardState.Incomplete
        }
    }

    fun resetBoard() {
        scope.launch {
            cells.emit(emptyBoard)
        }
    }

    private fun toResult(cellState: CellState, lineState: LineState) = when (cellState) {
        is CellState.Circle -> BoardState.CircleWon(lineState)
        is CellState.Cross -> BoardState.CrossWon(lineState)
        is CellState.Empty -> BoardState.Incomplete
    }
}