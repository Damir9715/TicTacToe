package com.gamesharp.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

//@Composable
//fun Lines() {
//    ConstraintLayout(
//        Modifier
//            .size(320.dp)
//            .border(2.dp, Color.Red)
//    ) {
//        val (line1, line2, line3, line4) = createRefs()
//
//        Box(
//            modifier = Modifier
//                .constrainAs(line1) {
//                    top.linkTo(parent.top)
//                    bottom.linkTo(line2.top)
//                }
//                .fillMaxWidth()
//                .height(LINE_WIDTH)
//                .background(MaterialTheme.colors.onSurface)
//        )
//        Box(
//            modifier = Modifier
//                .constrainAs(line2) {
//                    top.linkTo(line1.bottom)
//                    bottom.linkTo(parent.bottom)
//                }
//                .fillMaxWidth()
//                .height(LINE_WIDTH)
//                .background(MaterialTheme.colors.onSurface)
//        )
//
//        Box(
//            modifier = Modifier
//                .constrainAs(line3) {
//                    start.linkTo(parent.start)
//                    end.linkTo(line4.start)
//                }
//                .width(LINE_WIDTH)
//                .fillMaxHeight()
//                .background(MaterialTheme.colors.onSurface)
//        )
//        Box(
//            modifier = Modifier
//                .constrainAs(line4) {
//                    start.linkTo(line3.end)
//                    end.linkTo(parent.end)
//                }
//                .width(LINE_WIDTH)
//                .fillMaxHeight()
//                .background(MaterialTheme.colors.onSurface)
//        )
//    }
//}