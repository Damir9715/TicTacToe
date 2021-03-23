package com.gamesharp.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamesharp.tictactoe.ui.components.Info
import com.gamesharp.tictactoe.ui.components.Outcome
import com.gamesharp.tictactoe.ui.components.Playground
import com.gamesharp.tictactoe.ui.theme.TicTacToeTheme

val LINE_WIDTH = 8.dp
val PLAYGROUND_SIZE = 324.dp
val CELL_SIZE = 108.dp
val FIGURE_SIZE = 80.dp

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TicTacToeTheme {
                Surface(color = MaterialTheme.colors.surface, modifier = Modifier.fillMaxSize()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Info(viewModel)
                        if (viewModel.isGameEnded.collectAsState().value) {
                            Outcome(viewModel)
                        } else {
                            Playground(viewModel)
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.onSurface),
                            modifier = Modifier
                                .padding(top = 40.dp)
                                .height(50.dp)
                                .width(100.dp),
                            enabled = viewModel.isResetClickable.collectAsState().value,
                            onClick = viewModel::onReset
                        ) {
                            Text(
                                text = "Retry",
                                fontSize = 20.sp,
                                color = MaterialTheme.colors.secondary
                            )
                        }
                    }
                }
            }
        }
    }
}