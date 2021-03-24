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
import com.gamesharp.tictactoe.MainViewModel
import com.gamesharp.tictactoe.R
import com.gamesharp.tictactoe.model.BoardState

private val OUTCOME_ICON_SIZE = 200.dp

@Composable
fun Outcome() {
    val viewModel: MainViewModel = viewModel()

    Column(
        modifier = Modifier
            .size(PLAYGROUND_SIZE)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (viewModel.boardState.collectAsState().value) {
            is BoardState.Incomplete -> Unit
            is BoardState.CircleWon -> {
                Image(
                    modifier = Modifier.size(OUTCOME_ICON_SIZE),
                    painter = painterResource(R.drawable.ic_circle),
                    contentDescription = null,
                )
                Text(text = "WINS!", fontSize = 40.sp, color = MaterialTheme.colors.secondary)
            }
            is BoardState.CrossWon -> {
                Image(
                    modifier = Modifier.size(OUTCOME_ICON_SIZE),
                    painter = painterResource(R.drawable.ic_cross),
                    contentDescription = null,
                )
                Text(text = "WINS!", fontSize = 40.sp, color = MaterialTheme.colors.secondary)
            }
            is BoardState.Draw -> {
                Row {
                    Image(
                        modifier = Modifier.size(OUTCOME_ICON_SIZE),
                        painter = painterResource(R.drawable.ic_cross),
                        contentDescription = null,
                    )
                    Image(
                        modifier = Modifier.size(OUTCOME_ICON_SIZE),
                        painter = painterResource(R.drawable.ic_circle),
                        contentDescription = null,
                    )
                }
                Text(text = "DRAW!", fontSize = 40.sp, color = MaterialTheme.colors.secondary)
            }
        }
    }
}