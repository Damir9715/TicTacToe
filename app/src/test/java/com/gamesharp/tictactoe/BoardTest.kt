package com.gamesharp.tictactoe

import com.gamesharp.tictactoe.model.Board
import com.gamesharp.tictactoe.model.BoardState
import com.gamesharp.tictactoe.model.CellState
import com.gamesharp.tictactoe.model.LineState
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class BoardTest {

    private val emptyBoard = listOf(
        CellState.Empty, CellState.Empty, CellState.Empty,
        CellState.Empty, CellState.Empty, CellState.Empty,
        CellState.Empty, CellState.Empty, CellState.Empty,
    )
    private val fullCrossesBoard = listOf(
        CellState.Cross, CellState.Cross, CellState.Cross,
        CellState.Cross, CellState.Cross, CellState.Cross,
        CellState.Cross, CellState.Cross, CellState.Cross,
    )
    private val fullCirclesBoard = listOf(
        CellState.Circle, CellState.Circle, CellState.Circle,
        CellState.Circle, CellState.Circle, CellState.Circle,
        CellState.Circle, CellState.Circle, CellState.Circle,
    )

    private val circleWinsBoard1 = listOf(
        CellState.Circle, CellState.Circle, CellState.Circle, //here
        CellState.Empty, CellState.Cross, CellState.Cross,
        CellState.Empty, CellState.Empty, CellState.Empty,
    )
    private val circleWinsBoard2 = listOf(
        CellState.Empty, CellState.Cross, CellState.Cross,
        CellState.Circle, CellState.Circle, CellState.Circle, //here
        CellState.Empty, CellState.Empty, CellState.Empty,
    )
    private val circleWinsBoard3 = listOf(
        CellState.Empty, CellState.Cross, CellState.Cross,
        CellState.Empty, CellState.Cross, CellState.Empty,
        CellState.Circle, CellState.Circle, CellState.Circle, //here
    )

    private val crossWinsBoard1 = listOf(
        CellState.Cross, CellState.Cross, CellState.Cross, //here
        CellState.Empty, CellState.Circle, CellState.Circle,
        CellState.Empty, CellState.Empty, CellState.Empty,
    )
    private val crossWinsBoard2 = listOf(
        CellState.Empty, CellState.Circle, CellState.Circle,
        CellState.Cross, CellState.Cross, CellState.Cross, //here
        CellState.Empty, CellState.Empty, CellState.Empty,
    )
    private val crossWinsBoard3 = listOf(
        CellState.Empty, CellState.Circle, CellState.Circle,
        CellState.Empty, CellState.Circle, CellState.Empty,
        CellState.Cross, CellState.Cross, CellState.Cross, //here
    )

    private val continueBoard1 = listOf(
        CellState.Cross, CellState.Circle, CellState.Cross,
        CellState.Empty, CellState.Empty, CellState.Empty,
        CellState.Empty, CellState.Empty, CellState.Empty,
    )
    private val continueBoard2 = listOf(
        CellState.Empty, CellState.Circle, CellState.Empty,
        CellState.Circle, CellState.Cross, CellState.Cross,
        CellState.Circle, CellState.Cross, CellState.Empty,
    )
    private val continueBoard3 = listOf(
        CellState.Circle, CellState.Circle, CellState.Empty,
        CellState.Cross, CellState.Cross, CellState.Circle,
        CellState.Cross, CellState.Circle, CellState.Cross,
    )

    private val drawBoard1 = listOf(
        CellState.Circle, CellState.Cross, CellState.Circle,
        CellState.Circle, CellState.Cross, CellState.Cross,
        CellState.Cross, CellState.Circle, CellState.Cross,
    )
    private val drawBoard2 = listOf(
        CellState.Cross, CellState.Circle, CellState.Cross,
        CellState.Circle, CellState.Cross, CellState.Cross,
        CellState.Circle, CellState.Cross, CellState.Circle,
    )
    private val drawBoard3 = listOf(
        CellState.Cross, CellState.Circle, CellState.Cross,
        CellState.Cross, CellState.Cross, CellState.Circle,
        CellState.Circle, CellState.Cross, CellState.Circle,
    )

    //unreal cases
//    @Test
//    fun `full empty board returns CONTINEUE`() {
//        val result = checkBoardState(emptyBoard)
//        assertThat(result).isEqualTo(BoardState.Incomplete)
//    }
//
//    @Test
//    fun `full circle board returns CIRCLE`() {
//        val result = checkBoardState(fullCirclesBoard)
//        assertThat(result).isEqualTo(BoardState.CrossWon(LineState.Row1))
//    }
//
//    @Test
//    fun `full cross board returns CROSS`() {
//        val result = checkBoardState(fullCrossesBoard)
//        assertThat(result).isEqualTo(BoardState.CrossWon(LineState.Row1))
//    }
//
//    //circle test
//    @Test
//    fun `first row of circle figures returns CIRCLE`() {
//        val result = checkBoardState(circleWinsBoard1)
//        assertThat(result).isEqualTo(BoardState.CircleWon(LineState.Row1))
//    }
//
//    @Test
//    fun `second row of circle figures returns CIRCLE`() {
//        val result = checkBoardState(circleWinsBoard2)
//        assertThat(result).isEqualTo(BoardState.CircleWon(LineState.Row2))
//    }
//
//    @Test
//    fun `third row of circle figures returns CIRCLE`() {
//        val result = checkBoardState(circleWinsBoard3)
//        assertThat(result).isEqualTo(BoardState.CircleWon(LineState.Row3))
//    }
//
//    //cross test
//    @Test
//    fun `first row of cross figures returns CROSS`() {
//        val result = checkBoardState(crossWinsBoard1)
//        assertThat(result).isEqualTo(BoardState.CrossWon(LineState.Row1))
//    }
//
//    @Test
//    fun `second row of cross figures returns CROSS`() {
//        val result = checkBoardState(crossWinsBoard2)
//        assertThat(result).isEqualTo(BoardState.CrossWon(LineState.Row2))
//    }
//
//    @Test
//    fun `third row of cross figures returns CROSS`() {
//        val result = checkBoardState(crossWinsBoard3)
//        assertThat(result).isEqualTo(BoardState.CrossWon(LineState.Row3))
//    }
//
//    //continue
//    @Test
//    fun `first case of different figures and not full board returns CONTINUE`() {
//        val result = checkBoardState(continueBoard1)
//        assertThat(result).isEqualTo(BoardState.Incomplete)
//    }
//
//    @Test
//    fun `second case of different figures and not full board returns CONTINUE`() {
//        val result = checkBoardState(continueBoard2)
//        assertThat(result).isEqualTo(BoardState.Incomplete)
//    }
//
//    @Test
//    fun `third case of different figures and not full board returns CONTINUE`() {
//        val result = checkBoardState(continueBoard3)
//        assertThat(result).isEqualTo(BoardState.Incomplete)
//    }
//
//    //draw
//    @Test
//    fun `first case of different figures and full board returns DRAW`() {
//        val result = checkBoardState(drawBoard1)
//        assertThat(result).isEqualTo(BoardState.Draw)
//    }
//
//    @Test
//    fun `second case of different figures and full board returns DRAW`() {
//        val result = checkBoardState(drawBoard2)
//        assertThat(result).isEqualTo(BoardState.Draw)
//    }
//
//    @Test
//    fun `third case of different figures and full board returns DRAW`() {
//        val result = checkBoardState(drawBoard3)
//        assertThat(result).isEqualTo(BoardState.Draw)
//    }
}