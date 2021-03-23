package com.gamesharp.tictactoe.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gamesharp.tictactoe.CELL_SIZE
import com.gamesharp.tictactoe.MainViewModel
import com.gamesharp.tictactoe.PLAYGROUND_SIZE

@Composable
fun Figures(
    viewModel: MainViewModel,
) {
    Column(modifier = Modifier.size(PLAYGROUND_SIZE)) {
        Row(
            Modifier
                .height(CELL_SIZE)
                .fillMaxWidth()
        ) {
            Figure(viewModel, 0)
            Figure(viewModel, 1)
            Figure(viewModel, 2)
        }
        Row(
            Modifier
                .height(CELL_SIZE)
                .fillMaxWidth()
        ) {
            Figure(viewModel, 3)
            Figure(viewModel, 4)
            Figure(viewModel, 5)
        }
        Row(
            Modifier
                .height(CELL_SIZE)
                .fillMaxWidth()) {
            Figure(viewModel, 6)
            Figure(viewModel, 7)
            Figure(viewModel, 8)
        }
    }
}