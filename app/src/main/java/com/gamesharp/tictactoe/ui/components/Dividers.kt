package com.gamesharp.tictactoe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val LINE_WIDTH = 8.dp

@Composable
fun Dividers() {
    Column(
        modifier = Modifier.size(PLAYGROUND_SIZE),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(LINE_WIDTH)
                .background(MaterialTheme.colors.onSurface)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(LINE_WIDTH)
                .background(MaterialTheme.colors.onSurface)
        )
    }

    Row(
        modifier = Modifier.size(PLAYGROUND_SIZE),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .width(LINE_WIDTH)
                .fillMaxHeight()
                .background(MaterialTheme.colors.onSurface)
        )
        Box(
            modifier = Modifier
                .width(LINE_WIDTH)
                .fillMaxHeight()
                .background(MaterialTheme.colors.onSurface)
        )
    }
}