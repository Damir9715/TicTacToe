package com.gamesharp.tictactoe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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