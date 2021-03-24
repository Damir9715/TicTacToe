package com.gamesharp.tictactoe.model

sealed class GameState {
    object Start : GameState()
    object Game : GameState()
    object Ending : GameState()
    object Result : GameState()
}