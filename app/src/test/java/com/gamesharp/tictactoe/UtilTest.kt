package com.gamesharp.tictactoe

import com.google.common.truth.Truth.assertThat
import org.junit.Test

//todo rethink
class UtilTest {

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
    @Test
    fun `full empty board returns CONTINEUE`() {
        val result = checkGameState(emptyBoard)
        assertThat(result).isEqualTo(GameCheckResult.Incomplete(LineState.Empty))
    }

    @Test
    fun `full circle board returns CIRCLE`() {
        val result = checkGameState(fullCirclesBoard)
        assertThat(result).isEqualTo(GameCheckResult.Circle(LineState.Row1))
    }

    @Test
    fun `full cross board returns CROSS`() {
        val result = checkGameState(fullCrossesBoard)
        assertThat(result).isEqualTo(GameCheckResult.Cross(LineState.Row1))
    }

    //circle test
    @Test
    fun `first row of circle figures returns CIRCLE`() {
        val result = checkGameState(circleWinsBoard1)
        assertThat(result).isEqualTo(GameCheckResult.Circle(LineState.Row1))
    }

    @Test
    fun `second row of circle figures returns CIRCLE`() {
        val result = checkGameState(circleWinsBoard2)
        assertThat(result).isEqualTo(GameCheckResult.Circle(LineState.Row2))
    }

    @Test
    fun `third row of circle figures returns CIRCLE`() {
        val result = checkGameState(circleWinsBoard3)
        assertThat(result).isEqualTo(GameCheckResult.Circle(LineState.Row3))
    }

    //cross test
    @Test
    fun `first row of cross figures returns CROSS`() {
        val result = checkGameState(crossWinsBoard1)
        assertThat(result).isEqualTo(GameCheckResult.Cross(LineState.Row1))
    }

    @Test
    fun `second row of cross figures returns CROSS`() {
        val result = checkGameState(crossWinsBoard2)
        assertThat(result).isEqualTo(GameCheckResult.Cross(LineState.Row2))
    }

    @Test
    fun `third row of cross figures returns CROSS`() {
        val result = checkGameState(crossWinsBoard3)
        assertThat(result).isEqualTo(GameCheckResult.Cross(LineState.Row3))
    }

    //continue
    @Test
    fun `first case of different figures and not full board returns CONTINUE`() {
        val result = checkGameState(continueBoard1)
        assertThat(result).isEqualTo(GameCheckResult.Incomplete(LineState.Empty))
    }

    @Test
    fun `second case of different figures and not full board returns CONTINUE`() {
        val result = checkGameState(continueBoard2)
        assertThat(result).isEqualTo(GameCheckResult.Incomplete(LineState.Empty))
    }

    @Test
    fun `third case of different figures and not full board returns CONTINUE`() {
        val result = checkGameState(continueBoard3)
        assertThat(result).isEqualTo(GameCheckResult.Incomplete(LineState.Empty))
    }

    //draw
    @Test
    fun `first case of different figures and full board returns DRAW`() {
        val result = checkGameState(drawBoard1)
        assertThat(result).isEqualTo(GameCheckResult.Draw(LineState.Empty))
    }

    @Test
    fun `second case of different figures and full board returns DRAW`() {
        val result = checkGameState(drawBoard2)
        assertThat(result).isEqualTo(GameCheckResult.Draw(LineState.Empty))
    }

    @Test
    fun `third case of different figures and full board returns DRAW`() {
        val result = checkGameState(drawBoard3)
        assertThat(result).isEqualTo(GameCheckResult.Draw(LineState.Empty))
    }
}