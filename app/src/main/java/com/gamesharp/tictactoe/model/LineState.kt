package com.gamesharp.tictactoe.model

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