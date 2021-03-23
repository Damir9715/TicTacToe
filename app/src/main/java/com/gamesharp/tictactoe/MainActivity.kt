package com.gamesharp.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamesharp.tictactoe.ui.theme.TicTacToeTheme

private val LINE_WIDTH = 8.dp
private val PLAYGROUND_SIZE = 324.dp
private val CELL_SIZE = 108.dp
private val FIGURE_SIZE = 80.dp

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TicTacToeTheme {
                Surface(color = MaterialTheme.colors.surface, modifier = Modifier.fillMaxSize()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Info("Start game 'X'", 1, 0)
                        Playground(onClick = {
                            this@MainActivity.toast(it.toString())
                        })
                        Button(
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.onSurface),
                            modifier = Modifier
                                .padding(top = 40.dp)
                                .height(50.dp)
                                .width(100.dp),
                            onClick = { this@MainActivity.toast("retry") }) {
                            Text(
                                text = "Retry",
                                fontSize = 20.sp,
                                color = MaterialTheme.colors.onSecondary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Info(infoState: String, crossScore: Int, circleScore: Int) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(elevation = 5.dp) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .height(70.dp)
                        .width(90.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "X - $crossScore", color = Color.Black, fontSize = 30.sp)
                }
            }
            Surface(elevation = 5.dp) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .height(70.dp)
                        .width(90.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "O - $circleScore", color = Color.Black, fontSize = 30.sp)
                }
            }
        }
        Text(text = infoState, color = Color.White, fontSize = 18.sp)
    }
}

@Composable
fun Playground(onClick: (Int) -> Unit) {
    Box(Modifier.padding(top = 50.dp), contentAlignment = Alignment.Center) {
        Lines()
        Shapes(onClick)
    }
}

@Composable
fun Shapes(onClick: (Int) -> Unit) {
    Column(modifier = Modifier.size(PLAYGROUND_SIZE)) {
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
                .fillMaxWidth()
        ) {
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