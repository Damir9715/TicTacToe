package com.gamesharp.tictactoe

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

}


sealed class GameState {
    object Start : GameState() //text, empty cells,
    object Game : GameState()
    object End : GameState()
}