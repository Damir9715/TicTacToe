package com.gamesharp.tictactoe.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gamesharp.tictactoe.MainViewModel
import com.gamesharp.tictactoe.model.BoardState
import com.gamesharp.tictactoe.model.LineState

private const val STROKE_WIDTH = 18f
val PLAYGROUND_SIZE = 324.dp

@Composable
fun Playground() {
    val viewModel: MainViewModel = viewModel()
    val color = when (viewModel.board.boardState.collectAsState().value) {
        is BoardState.CircleWon -> MaterialTheme.colors.onSecondary
        else -> MaterialTheme.colors.secondary
    }
    val lineState = viewModel.drawLine.collectAsState().value

    Box(Modifier.padding(top = 50.dp), contentAlignment = Alignment.Center) {
        Dividers()
        Figures()
        Canvas(modifier = Modifier.size(PLAYGROUND_SIZE)) {
            val lineLength = size.width
            val oneSixth = lineLength / 6

            when (lineState) {
                is LineState.Row1 -> drawLine(
                    start = Offset(x = 0f, y = oneSixth),
                    end = Offset(x = lineLength, y = oneSixth),
                    color = color,
                    strokeWidth = STROKE_WIDTH
                )
                is LineState.Row2 -> drawLine(
                    start = Offset(x = 0f, y = oneSixth * 3),
                    end = Offset(x = lineLength, y = oneSixth * 3),
                    color = color,
                    strokeWidth = STROKE_WIDTH
                )
                is LineState.Row3 -> drawLine(
                    start = Offset(x = 0f, y = oneSixth * 5),
                    end = Offset(x = lineLength, y = oneSixth * 5),
                    color = color,
                    strokeWidth = STROKE_WIDTH
                )
                is LineState.Column1 -> drawLine(
                    start = Offset(x = oneSixth, y = 0f),
                    end = Offset(x = oneSixth, y = lineLength),
                    color = color,
                    strokeWidth = STROKE_WIDTH
                )
                is LineState.Column2 -> drawLine(
                    start = Offset(x = oneSixth * 3, y = 0f),
                    end = Offset(x = oneSixth * 3, y = lineLength),
                    color = color,
                    strokeWidth = STROKE_WIDTH
                )
                is LineState.Column3 -> drawLine(
                    start = Offset(x = oneSixth * 5, y = 0f),
                    end = Offset(x = oneSixth * 5, y = lineLength),
                    color = color,
                    strokeWidth = STROKE_WIDTH
                )
                is LineState.Cross1 -> drawLine(
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = lineLength, y = lineLength),
                    color = color,
                    strokeWidth = STROKE_WIDTH
                )
                is LineState.Cross2 -> drawLine(
                    start = Offset(x = lineLength, y = 0f),
                    end = Offset(x = 0f, y = lineLength),
                    color = color,
                    strokeWidth = STROKE_WIDTH
                )
                is LineState.Empty -> Unit
            }
        }
    }
}