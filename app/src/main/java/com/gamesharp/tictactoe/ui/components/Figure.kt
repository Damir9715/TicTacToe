package com.gamesharp.tictactoe.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.gamesharp.tictactoe.*

@Composable
fun Figure(
    viewModel: MainViewModel,
    index: Int,
) {
    //fixme recomposes for each click, skip not clicked cells
    val board: List<Cell> by viewModel.board.collectAsState()
    val cell = board[index]

    Box(
        modifier = Modifier
            .size(CELL_SIZE)
            .fillMaxWidth()
            .clickable(enabled = viewModel.isFigureClickable.collectAsState().value) {
                if (cell == Cell.Empty) {
                    viewModel.onClick(ClickData(index, viewModel.currentPlayer.value))
                }
            },
        contentAlignment = Alignment.Center
    ) {
        when (cell) {
            is Cell.Empty -> Unit
            is Cell.Circle -> Image(
                painter = painterResource(R.drawable.ic_circle),
                contentDescription = null,
                modifier = Modifier.size(FIGURE_SIZE)
            )
            is Cell.Cross -> Image(
                painter = painterResource(R.drawable.ic_cross),
                contentDescription = null,
                modifier = Modifier.size(FIGURE_SIZE)
            )
        }
    }
}