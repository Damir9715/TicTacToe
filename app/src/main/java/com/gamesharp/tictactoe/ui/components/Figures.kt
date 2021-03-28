package com.gamesharp.tictactoe.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gamesharp.tictactoe.MainViewModel

@Composable
fun Figures() {
    val viewModel: MainViewModel = viewModel()
    val board by viewModel.cells.collectAsState()

    Column(modifier = Modifier.size(PLAYGROUND_SIZE)) {
        repeat(3) { row ->
            Row(
                Modifier
                    .height(CELL_SIZE)
                    .fillMaxWidth()
            ) {
                repeat(3) { column ->
                    Figure(row * 3 + column, board[row * 3 + column])
                }
            }
        }
    }
}