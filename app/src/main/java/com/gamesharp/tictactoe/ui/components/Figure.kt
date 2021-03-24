package com.gamesharp.tictactoe.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gamesharp.tictactoe.MainViewModel
import com.gamesharp.tictactoe.R
import com.gamesharp.tictactoe.model.BoardState
import com.gamesharp.tictactoe.model.CellState
import kotlinx.coroutines.launch

val CELL_SIZE = 108.dp
private val FIGURE_SIZE = 80.dp

@Composable
fun Figure(index: Int) {
    //fixme recomposes for each click, skip not clicked cells
    val viewModel: MainViewModel = viewModel()
    val board by viewModel.cells.collectAsState()
    val cell = board[index]
    val enabled = viewModel.boardState.collectAsState().value == BoardState.Incomplete
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .size(CELL_SIZE)
            .fillMaxWidth()
            .clickable(enabled = enabled) {
                if (cell == CellState.Empty) {
                    scope.launch {
                        viewModel.click.emit(index to viewModel.currentPlayer.value)
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        when (cell) {
            is CellState.Empty -> Unit
            is CellState.Circle -> Image(
                painter = painterResource(R.drawable.ic_circle),
                contentDescription = null,
                modifier = Modifier.size(FIGURE_SIZE)
            )
            is CellState.Cross -> Image(
                painter = painterResource(R.drawable.ic_cross),
                contentDescription = null,
                modifier = Modifier.size(FIGURE_SIZE)
            )
        }
    }
}