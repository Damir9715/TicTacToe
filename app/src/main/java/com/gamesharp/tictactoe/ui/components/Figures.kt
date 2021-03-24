package com.gamesharp.tictactoe.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Figures() {
    Column(modifier = Modifier.size(PLAYGROUND_SIZE)) {
        Row(
            Modifier
                .height(CELL_SIZE)
                .fillMaxWidth()
        ) {
            Figure(0)
            Figure(1)
            Figure(2)
        }
        Row(
            Modifier
                .height(CELL_SIZE)
                .fillMaxWidth()
        ) {
            Figure(3)
            Figure(4)
            Figure(5)
        }
        Row(
            Modifier
                .height(CELL_SIZE)
                .fillMaxWidth()
        ) {
            Figure(6)
            Figure(7)
            Figure(8)
        }
    }
}