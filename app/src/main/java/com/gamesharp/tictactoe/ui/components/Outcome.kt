package com.gamesharp.tictactoe.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gamesharp.tictactoe.CellState
import com.gamesharp.tictactoe.MainViewModel
import com.gamesharp.tictactoe.PLAYGROUND_SIZE
import com.gamesharp.tictactoe.R

@Composable
fun Outcome() {
    val viewModel: MainViewModel = viewModel()
    //todo review it
    val drawableRes: Int = when (viewModel.setLineColor.collectAsState().value) {
        is CellState.Circle -> R.drawable.ic_circle
        is CellState.Cross -> R.drawable.ic_cross
        else -> -1
    }

    Column(
        modifier = Modifier
            .size(PLAYGROUND_SIZE)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (drawableRes == -1) {
            Row {
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(R.drawable.ic_cross),
                    contentDescription = null,
                )
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(R.drawable.ic_circle),
                    contentDescription = null,
                )
            }
            Text(text = "DRAW!", fontSize = 40.sp, color = MaterialTheme.colors.secondary)
        } else {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(drawableRes),
                contentDescription = null,
            )
            Text(text = "WINS!", fontSize = 40.sp, color = MaterialTheme.colors.secondary)
        }
    }
}