package com.perisic.luka.jamb

import com.perisic.luka.jamb.data.JambGame
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testOnes() {
        val inputString = "44253311"
        val regex = "(\\d)\\1{1}\\d+(?!\\1)(\\d)\\2{1}".toRegex()
        val match = regex.find(inputString)!!
        println(match.value)
        println(match.range)

    }

    @Test
    fun testGameCreate() {
        val game = JambGame(1) {
            println("PLAYER: ${it.name}")
            println("FIELDS:")
            println(it.cells.joinToString(separator = "\n") { field -> "FILLED: ${field.filled}, VALUE: ${field.value}" })
            println("DICES:")
            println(it.dices.joinToString(separator = "\n") { dice -> "LOCKED: ${dice.locked}, VALUE: ${dice.value}" })
            println("-----------------------------------------")
        }
        game.roll()
        game.lockDice(0)
        game.lockDice(1)
        game.lockDice(2)
        game.roll()
        game.roll()
        game.choose(3)
        game.roll()
        game.roll()
        game.choose(6)
    }

}
