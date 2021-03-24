package com.gamesharp.tictactoe.model

class Board(
    val board: List<CellState> = listOf<CellState>(
        CellState.Empty, CellState.Empty, CellState.Empty,
        CellState.Empty, CellState.Empty, CellState.Empty,
        CellState.Empty, CellState.Empty, CellState.Empty,
    )
) {
    val boardState: BoardState
        get() {
            if (board.filterNot { it == CellState.Empty }.count() < 5) {
                return BoardState.Incomplete(LineState.Empty)
            }

            //rows
            if (board[0] == board[1] && board[1] == board[2] && board[0] != CellState.Empty) {
                return toResult(board[0], LineState.Row1)
            }
            if (board[3] == board[4] && board[4] == board[5] && board[3] != CellState.Empty) {
                return toResult(board[3], LineState.Row2)
            }
            if (board[6] == board[7] && board[7] == board[8] && board[6] != CellState.Empty) {
                return toResult(board[6], LineState.Row3)
            }

            //columns
            if (board[0] == board[3] && board[3] == board[6] && board[0] != CellState.Empty) {
                return toResult(board[0], LineState.Column1)
            }
            if (board[1] == board[4] && board[4] == board[7] && board[1] != CellState.Empty) {
                return toResult(board[1], LineState.Column2)
            }
            if (board[2] == board[5] && board[5] == board[8] && board[2] != CellState.Empty) {
                return toResult(board[2], LineState.Column3)
            }

            //cross
            if (board[0] == board[4] && board[4] == board[8] && board[0] != CellState.Empty) {
                return toResult(board[0], LineState.Cross1)
            }
            if (board[2] == board[4] && board[4] == board[6] && board[2] != CellState.Empty) {
                return toResult(board[2], LineState.Cross2)
            }

            //end
            return if (board.filter { it == CellState.Empty }.count() == 0) {
                BoardState.Draw(LineState.Empty)
            } else {
                BoardState.Incomplete(LineState.Empty)
            }
        }

    private fun toResult(cellState: CellState, state: LineState) = when (cellState) {
        is CellState.Circle -> BoardState.CircleWon(state)
        is CellState.Cross -> BoardState.CrossWon(state)
        //never
        else -> BoardState.Incomplete(LineState.Empty)

    }
}