package com.gamesharp.tictactoe

fun checkGameState(list: List<CellState>): GameCheckResult {
    if (list.filterNot { it == CellState.Empty }.count() < 5) {
        return GameCheckResult.Incomplete(LineState.Empty)
    }

    //rows
    if (list[0] == list[1] && list[1] == list[2] && list[0] != CellState.Empty) {
        return toResult(list[0], LineState.Row1)
    }
    if (list[3] == list[4] && list[4] == list[5] && list[3] != CellState.Empty) {
        return toResult(list[3], LineState.Row2)
    }
    if (list[6] == list[7] && list[7] == list[8] && list[6] != CellState.Empty) {
        return toResult(list[6], LineState.Row3)
    }

    //columns
    if (list[0] == list[3] && list[3] == list[6] && list[0] != CellState.Empty) {
        return toResult(list[0], LineState.Column1)
    }
    if (list[1] == list[4] && list[4] == list[7] && list[1] != CellState.Empty) {
        return toResult(list[1], LineState.Column2)
    }
    if (list[2] == list[5] && list[5] == list[8] && list[2] != CellState.Empty) {
        return toResult(list[2], LineState.Column3)
    }

    //cross
    if (list[0] == list[4] && list[4] == list[8] && list[0] != CellState.Empty) {
        return toResult(list[0], LineState.Cross1)
    }
    if (list[2] == list[4] && list[4] == list[6] && list[2] != CellState.Empty) {
        return toResult(list[2], LineState.Cross2)
    }

    //end
    return if (list.filter { it == CellState.Empty }.count() == 0) {
        GameCheckResult.Draw(LineState.Empty)
    } else {
        GameCheckResult.Incomplete(LineState.Empty)
    }
}

fun toResult(cellState: CellState, state: LineState) = when (cellState) {
    is CellState.Circle -> GameCheckResult.Circle(state)
    is CellState.Cross -> GameCheckResult.Cross(state)
    //never
    else -> GameCheckResult.Incomplete(LineState.Empty)
}

sealed class GameCheckResult {
    data class Incomplete(val state: LineState) : GameCheckResult()
    data class Circle(val state: LineState) : GameCheckResult()
    data class Cross(val state: LineState) : GameCheckResult()
    data class Draw(val state: LineState) : GameCheckResult()
}

sealed class LineState {
    object Empty : LineState()
    object Row1 : LineState()
    object Row2 : LineState()
    object Row3 : LineState()
    object Column1 : LineState()
    object Column2 : LineState()
    object Column3 : LineState()
    object Cross1 : LineState()
    object Cross2 : LineState()
}