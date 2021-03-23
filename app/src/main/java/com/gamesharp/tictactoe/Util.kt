package com.gamesharp.tictactoe

import android.app.Activity
import android.widget.Toast

fun Activity.toast(text: String, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, length).show()


fun checkGameState(list: List<Cell>): GameCheckResult {
    if (list.filterNot { it == Cell.Empty }.count() < 5) {
        return GameCheckResult.Continue
    }

    //rows
    if (list[0] == list[1] && list[1] == list[2] && list[0] != Cell.Empty) {
        return toResult(list[0])
    }
    if (list[3] == list[4] && list[4] == list[5] && list[3] != Cell.Empty) {
        return toResult(list[3])
    }
    if (list[6] == list[7] && list[7] == list[8] && list[6] != Cell.Empty) {
        return toResult(list[6])
    }

    //columns
    if (list[0] == list[3] && list[3] == list[6] && list[0] != Cell.Empty) {
        return toResult(list[0])
    }
    if (list[1] == list[4] && list[4] == list[7] && list[1] != Cell.Empty) {
        return toResult(list[1])
    }
    if (list[2] == list[5] && list[5] == list[8] && list[2] != Cell.Empty) {
        return toResult(list[2])
    }

    //cross
    if (list[0] == list[4] && list[4] == list[8] && list[0] != Cell.Empty) {
        return toResult(list[0])
    }
    if (list[2] == list[4] && list[4] == list[6] && list[2] != Cell.Empty) {
        return toResult(list[2])
    }

    //end
    return if (list.filter { it == Cell.Empty }.count() == 0) {
        GameCheckResult.Draw
    } else {
        GameCheckResult.Continue
    }
}

fun toResult(cell: Cell) = when (cell) {
    is Cell.Circle -> GameCheckResult.Circle
    is Cell.Cross -> GameCheckResult.Cross
    //never
    else -> GameCheckResult.Continue
}

sealed class GameCheckResult {
    object Continue : GameCheckResult()
    object Circle : GameCheckResult()
    object Cross : GameCheckResult()
    object Draw : GameCheckResult()
}
