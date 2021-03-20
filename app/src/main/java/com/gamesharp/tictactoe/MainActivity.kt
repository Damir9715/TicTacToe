package com.gamesharp.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DimenRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gamesharp.tictactoe.ui.theme.TicTacToeTheme

private val LINE_WIDTH = 8.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                Surface(color = MaterialTheme.colors.surface) {
                    Screen()
                }
            }
        }
    }
}

@Composable
fun Screen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Playground()
    }
}

@Composable
fun Playground() {
    ConstraintLayout(Modifier.size(320.dp)) {
        val (line1, line2, line3, line4) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(line1) {
                    top.linkTo(parent.top)
                    bottom.linkTo(line2.top)
                }
                .fillMaxWidth()
                .height(LINE_WIDTH)
                .background(MaterialTheme.colors.onSurface)
        )
        Box(
            modifier = Modifier
                .constrainAs(line2) {
                    top.linkTo(line1.bottom)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
                .height(LINE_WIDTH)
                .background(MaterialTheme.colors.onSurface)
        )

        Box(
            modifier = Modifier
                .constrainAs(line3) {
                    start.linkTo(parent.start)
                    end.linkTo(line4.start)
                }
                .width(LINE_WIDTH)
                .fillMaxHeight()
                .background(MaterialTheme.colors.onSurface)
        )
        Box(
            modifier = Modifier
                .constrainAs(line4) {
                    start.linkTo(line3.end)
                    end.linkTo(parent.end)
                }
                .width(LINE_WIDTH)
                .fillMaxHeight()
                .background(MaterialTheme.colors.onSurface)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting() {
    Column {
        var name: String by remember { mutableStateOf("Hello") }

        Text(
            text = name,
            modifier = Modifier.padding(bottom = 10.dp),
            fontSize = 24.sp
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name") }
        )
    }
}