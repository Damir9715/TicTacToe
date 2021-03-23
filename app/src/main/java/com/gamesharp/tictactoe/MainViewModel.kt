package com.gamesharp.tictactoe

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

}


sealed class GameState {
    object Start : GameState()      //text, no-line, cells,             button unavailable
    object Game : GameState()       //text, no-line, cells,             button unavailable
    object Ending : GameState()     //text, line,    cells unavailable, button unavailable
    object Result : GameState()     //text, no-line, no-cells,          button available
}