package com.gamesharp.tictactoe.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamesharp.tictactoe.PLAYGROUND_SIZE
import com.gamesharp.tictactoe.R

@Composable
fun Outcome() {
    Column(
        modifier = Modifier
            .size(PLAYGROUND_SIZE)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.ic_cross),
            contentDescription = null,
        )
        Text(text = "WINS!", fontSize = 40.sp, color = MaterialTheme.colors.secondary)
    }
}