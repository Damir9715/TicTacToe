package com.gamesharp.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamesharp.tictactoe.ui.components.Info
import com.gamesharp.tictactoe.ui.components.Outcome
import com.gamesharp.tictactoe.ui.components.Playground
import com.gamesharp.tictactoe.ui.theme.TicTacToeTheme
import kotlinx.coroutines.launch

// FIX unexpected behavior on double click 'retry' when DRAW state
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TicTacToeTheme {
                Surface(color = MaterialTheme.colors.surface, modifier = Modifier.fillMaxSize()) {
                    val scope = rememberCoroutineScope()

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Info()
                        if (viewModel.isOutcomeVisible.collectAsState().value) {
                            Outcome()
                        } else {
                            Playground()
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.onSurface),
                            modifier = Modifier
                                .padding(top = 40.dp)
                                .height(50.dp)
                                .width(100.dp),
                            onClick = {
                                scope.launch {
                                    viewModel.reset.emit(Unit)
                                }
                            }
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