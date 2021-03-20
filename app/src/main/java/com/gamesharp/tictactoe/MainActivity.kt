package com.gamesharp.tictactoe

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gamesharp.tictactoe.ui.theme.TicTacToeTheme

private val LINE_WIDTH = 8.dp
private val PLAYGROUND_SIZE = 324.dp
private val CELL_SIZE = 108.dp
private val FIGURE_SIZE = 80.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                Surface(color = MaterialTheme.colors.surface) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Playground(onClick = {
                            Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show()
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun Playground(onClick: (Int) -> Unit) {
    Box {
        Lines()
        Shapes(onClick)
    }
}

@Composable
fun Shapes(onClick: (Int) -> Unit) {
    Column {
        Row(
            Modifier
                .height(CELL_SIZE)
                .fillMaxWidth()
        ) {
            Shape(ShapeState.Cross, 1, onClick)
            Shape(ShapeState.Circle, 2, onClick)
            Shape(ShapeState.Circle, 3, onClick)
        }
        Row(
            Modifier
                .height(CELL_SIZE)
                .fillMaxWidth()) {
            Shape(ShapeState.Circle, 4, onClick)
            Shape(ShapeState.Cross, 5, onClick)
            Shape(ShapeState.Circle, 6, onClick)
        }
        Row(
            Modifier
                .height(CELL_SIZE)
                .fillMaxWidth()
        ) {
            Shape(ShapeState.Empty, 7, onClick)
            Shape(ShapeState.Circle, 8, onClick)
            Shape(ShapeState.Cross, 9, onClick)
        }
    }
}

@Composable
fun Shape(
    state: ShapeState = ShapeState.Circle,
    cellNumber: Int,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .size(CELL_SIZE)
            .fillMaxWidth()
            .clickable { onClick(cellNumber) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        when (state) {
            is ShapeState.Empty -> Unit
            is ShapeState.Circle -> {
                Image(
                    modifier = Modifier.size(FIGURE_SIZE),
                    painter = painterResource(R.drawable.ic_circle),
                    contentDescription = null
                )
            }
            is ShapeState.Cross -> {
                Image(
                    modifier = Modifier.size(FIGURE_SIZE),
                    painter = painterResource(R.drawable.ic_cross),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun Lines() {
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

sealed class ShapeState {
    object Empty : ShapeState()
    object Circle : ShapeState()
    object Cross : ShapeState()
}