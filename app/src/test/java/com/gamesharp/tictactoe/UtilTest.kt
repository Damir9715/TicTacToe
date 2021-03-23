package com.gamesharp.tictactoe

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilTest {

    private val emptyBoard = listOf(
        Cell.Empty, Cell.Empty, Cell.Empty,
        Cell.Empty, Cell.Empty, Cell.Empty,
        Cell.Empty, Cell.Empty, Cell.Empty,
    )
    private val fullCrossesBoard = listOf(
        Cell.Cross, Cell.Cross, Cell.Cross,
        Cell.Cross, Cell.Cross, Cell.Cross,
        Cell.Cross, Cell.Cross, Cell.Cross,
    )
    private val fullCirclesBoard = listOf(
        Cell.Circle, Cell.Circle, Cell.Circle,
        Cell.Circle, Cell.Circle, Cell.Circle,
        Cell.Circle, Cell.Circle, Cell.Circle,
    )

    private val circleWinsBoard1 = listOf(
        Cell.Circle, Cell.Circle, Cell.Circle, //here
        Cell.Empty, Cell.Cross, Cell.Cross,
        Cell.Empty, Cell.Empty, Cell.Empty,
    )
    private val circleWinsBoard2 = listOf(
        Cell.Empty, Cell.Cross, Cell.Cross,
        Cell.Circle, Cell.Circle, Cell.Circle, //here
        Cell.Empty, Cell.Empty, Cell.Empty,
    )
    private val circleWinsBoard3 = listOf(
        Cell.Empty, Cell.Cross, Cell.Cross,
        Cell.Empty, Cell.Cross, Cell.Empty,
        Cell.Circle, Cell.Circle, Cell.Circle, //here
    )

    private val crossWinsBoard1 = listOf(
        Cell.Cross, Cell.Cross, Cell.Cross, //here
        Cell.Empty, Cell.Circle, Cell.Circle,
        Cell.Empty, Cell.Empty, Cell.Empty,
    )
    private val crossWinsBoard2 = listOf(
        Cell.Empty, Cell.Circle, Cell.Circle,
        Cell.Cross, Cell.Cross, Cell.Cross, //here
        Cell.Empty, Cell.Empty, Cell.Empty,
    )
    private val crossWinsBoard3 = listOf(
        Cell.Empty, Cell.Circle, Cell.Circle,
        Cell.Empty, Cell.Circle, Cell.Empty,
        Cell.Cross, Cell.Cross, Cell.Cross, //here
    )

    private val continueBoard1 = listOf(
        Cell.Cross, Cell.Circle, Cell.Cross,
        Cell.Empty, Cell.Empty, Cell.Empty,
        Cell.Empty, Cell.Empty, Cell.Empty,
    )
    private val continueBoard2 = listOf(
        Cell.Empty, Cell.Circle, Cell.Empty,
        Cell.Circle, Cell.Cross, Cell.Cross,
        Cell.Circle, Cell.Cross, Cell.Empty,
    )
    private val continueBoard3 = listOf(
        Cell.Circle, Cell.Circle, Cell.Empty,
        Cell.Cross, Cell.Cross, Cell.Circle,
        Cell.Cross, Cell.Circle, Cell.Cross,
    )

    private val drawBoard1 = listOf(
        Cell.Circle, Cell.Cross, Cell.Circle,
        Cell.Circle, Cell.Cross, Cell.Cross,
        Cell.Cross, Cell.Circle, Cell.Cross,
    )
    private val drawBoard2 = listOf(
        Cell.Cross, Cell.Circle, Cell.Cross,
        Cell.Circle, Cell.Cross, Cell.Cross,
        Cell.Circle, Cell.Cross, Cell.Circle,
    )
    private val drawBoard3 = listOf(
        Cell.Cross, Cell.Circle, Cell.Cross,
        Cell.Cross, Cell.Cross, Cell.Circle,
        Cell.Circle, Cell.Cross, Cell.Circle,
    )

    //unreal cases
    @Test
    fun `full empty board returns CONTINEUE`() {
        val result = checkGameState(emptyBoard)
        assertThat(result).isEqualTo(GameCheckResult.Continue)
    }

    @Test
    fun `full circle board returns CIRCLE`() {
        val result = checkGameState(fullCirclesBoard)
        assertThat(result).isEqualTo(GameCheckResult.Circle)
    }

    @Test
    fun `full cross board returns CROSS`() {
        val result = checkGameState(fullCrossesBoard)
        assertThat(result).isEqualTo(GameCheckResult.Cross)
    }

    //circle test
    @Test
    fun `first row of circle figures returns CIRCLE`() {
        val result = checkGameState(circleWinsBoard1)
        assertThat(result).isEqualTo(GameCheckResult.Circle)
    }

    @Test
    fun `second row of circle figures returns CIRCLE`() {
        val result = checkGameState(circleWinsBoard2)
        assertThat(result).isEqualTo(GameCheckResult.Circle)
    }

    @Test
    fun `third row of circle figures returns CIRCLE`() {
        val result = checkGameState(circleWinsBoard3)
        assertThat(result).isEqualTo(GameCheckResult.Circle)
    }

    //cross test
    @Test
    fun `first row of cross figures returns CROSS`() {
        val result = checkGameState(crossWinsBoard1)
        assertThat(result).isEqualTo(GameCheckResult.Cross)
    }

    @Test
    fun `second row of cross figures returns CROSS`() {
        val result = checkGameState(crossWinsBoard2)
        assertThat(result).isEqualTo(GameCheckResult.Cross)
    }

    @Test
    fun `third row of cross figures returns CROSS`() {
        val result = checkGameState(crossWinsBoard3)
        assertThat(result).isEqualTo(GameCheckResult.Cross)
    }

    //continue
    @Test
    fun `first case of different figures and not full board returns CONTINUE`() {
        val result = checkGameState(continueBoard1)
        assertThat(result).isEqualTo(GameCheckResult.Continue)
    }

    @Test
    fun `second case of different figures and not full board returns CONTINUE`() {
        val result = checkGameState(continueBoard2)
        assertThat(result).isEqualTo(GameCheckResult.Continue)
    }

    @Test
    fun `third case of different figures and not full board returns CONTINUE`() {
        val result = checkGameState(continueBoard3)
        assertThat(result).isEqualTo(GameCheckResult.Continue)
    }

    //draw
    @Test
    fun `first case of different figures and full board returns DRAW`() {
        val result = checkGameState(drawBoard1)
        assertThat(result).isEqualTo(GameCheckResult.Draw)
    }

    @Test
    fun `second case of different figures and full board returns DRAW`() {
        val result = checkGameState(drawBoard2)
        assertThat(result).isEqualTo(GameCheckResult.Draw)
    }

    @Test
    fun `third case of different figures and full board returns DRAW`() {
        val result = checkGameState(drawBoard3)
        assertThat(result).isEqualTo(GameCheckResult.Draw)
    }
}