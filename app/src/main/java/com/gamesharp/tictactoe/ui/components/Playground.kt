package com.gamesharp.tictactoe.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gamesharp.tictactoe.GameCheckResult
import com.gamesharp.tictactoe.MainViewModel

@Composable
fun Playground(
    viewModel: MainViewModel,
    checkResult: GameCheckResult
) {
    Box(Modifier.padding(top = 50.dp), contentAlignment = Alignment.Center) {
        val color = if (true) {
            MaterialTheme.colors.secondary
        } else {
            MaterialTheme.colors.onSecondary
        }
        Dividers()
        Figures(/*currentPlayer, board, onClick*/viewModel)
//        Canvas(modifier = Modifier.size(PLAYGROUND_SIZE)) {
//            val lineLength = size.width
//            val oneSixth = lineLength / 6
//
//            //row
//            drawLine(
//                start = Offset(x = 0f, y = oneSixth),
//                end = Offset(x = lineLength, y = oneSixth),
//                color = color,
//                strokeWidth = 18f
//            )
//            drawLine(
//                start = Offset(x = 0f, y = oneSixth * 3),
//                end = Offset(x = lineLength, y = oneSixth * 3),
//                color = color,
//                strokeWidth = 18f
//            )
//            drawLine(
//                start = Offset(x = 0f, y = oneSixth * 5),
//                end = Offset(x = lineLength, y = oneSixth * 5),
//                color = color,
//                strokeWidth = 18f
//            )
//
//            //column
//            drawLine(
//                start = Offset(x = oneSixth, y = 0f),
//                end = Offset(x = oneSixth, y = lineLength),
//                color = color,
//                strokeWidth = 18f
//            )
//            drawLine(
//                start = Offset(x = oneSixth * 3, y = 0f),
//                end = Offset(x = oneSixth * 3, y = lineLength),
//                color = color,
//                strokeWidth = 18f
//            )
//            drawLine(
//                start = Offset(x = oneSixth * 5, y = 0f),
//                end = Offset(x = oneSixth * 5, y = lineLength),
//                color = color,
//                strokeWidth = 18f
//            )
//
//            //cross
//            drawLine(
//                start = Offset(x = lineLength, y = 0f),
//                end = Offset(x = 0f, y = lineLength),
//                color = color,
//                strokeWidth = 18f
//            )
//            drawLine(
//                start = Offset(x = 0f, y = 0f),
//                end = Offset(x = lineLength, y = lineLength),
//                color = color,
//                strokeWidth = 18f
//            )
//        }
    }
}