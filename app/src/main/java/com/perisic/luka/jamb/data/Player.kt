package com.perisic.luka.jamb.data

import kotlin.random.Random

data class Player(
    val name: String
) {

    var cells: List<Cell> = createFields()
    var dices: List<Dice> = createDices()
    var buttonEnabled: Boolean = false
    var currentTurn: Int = 0

    private fun createFields(): List<Cell> {
        val list = arrayListOf<Cell>()
        for (i in 0 until 18) {
            list.add(
                Cell(
                    locked = arrayOf(6, 9, 17).contains(i),
                    display = arrayOf(6, 9, 17).contains(i),
                    validator = when {
                        i < 6 -> RegexValidator("(${i + 1})\\1*")
                        i == 6 -> object : Validator {
                            override fun validate(numbers: String): Int? {
                                return list.subList(0, 6).sumBy { it.value }
                            }
                        }
                        i == 7 || i == 8 -> RegexValidator("[0-9]+")
                        i == 9 -> object : Validator {
                            override fun validate(numbers: String): Int? {
                                return list[8].value - list[7].value
                            }
                        }
                        i == 10 -> RegexValidator("(\\d)\\1{1,}(?!\\1)(\\d)\\2{1,}")
                        i == 11 -> RegexValidator("(\\d)\\1{2}")
                        i == 12 -> RegexValidator("(\\d)\\1{1}(?!\\1)(\\d)\\2{2}|(\\d)\\3{2}(?!\\3)(\\d)\\4{1}")
                        i == 13 -> RegexValidator("4{1,}3{1,}2{1,}1{1,}|5{1,}4{1,}3{1,}2{1,}|6{1,}5{1,}4{1,}3{1,}")
                        i == 14 -> RegexValidator("5{1,}4{1,}3{1,}2{1,}1{1,}|6{1,}5{1,}4{1,}3{1,}2{1,}")
                        i == 15 -> RegexValidator("(\\d)\\1{3}")
                        i == 16 -> RegexValidator("(\\d)\\1{5}")
                        i == 17 -> object : Validator {
                            override fun validate(numbers: String): Int? {
                                return list.subList(10, 17).sumBy { it.value }
                            }
                        }
                        else -> object : Validator {
                            override fun validate(numbers: String): Int? {
                                return null
                            }
                        }
                    }
                )

            )
        }
        return list
    }

    private fun createDices(): List<Dice> {
        val list = arrayListOf<Dice>()
        for (i in 0 until 6) {
            list.add(Dice())
        }
        return list
    }

    fun roll(): Boolean {
        if (currentTurn != 3) {
            currentTurn++
            if (currentTurn == 1) {
                cells.forEach {
                    if (!it.filled) {
                        it.value = 0
                    }
                }
                cells.forEachIndexed { i, it ->
                    if (!it.filled) {
                        it.locked = arrayOf(6, 9, 17).contains(i)
                        if (!it.locked) {
                            it.value = 0
                        }
                    }
                }
                dices.forEach {
                    it.locked = false
                }
            }
            dices.forEach { dice ->
                if (!dice.locked) {
                    dice.value = Random.nextInt(1, 7)
                }
            }
            validate()
        }
        return currentTurn == 3
    }

    private fun validate() {
        val dicesCopy = ArrayList(dices.map { it })
        dicesCopy.sortByDescending { it.value }
        val numbers = dicesCopy.joinToString(separator = "") { dice -> dice.value.toString() }
        cells.forEach {
            if (!it.filled) {
                it.validator.validate(numbers)?.let { score ->
                    it.value = score
                }
            }
        }
    }

    fun start() {
        buttonEnabled = true
        currentTurn = 0
    }

    private fun lock() {
        dices.forEach { dice ->
            dice.locked = true
        }
        cells.forEach {
            if (!it.filled && !it.locked) {
                it.value = 0
            }
            it.locked = true
        }
    }

    fun lockDice(position: Int) {
        dices[position].locked = true
    }

    fun lockDices() {
        dices.forEach { it.locked = true }
        buttonEnabled = false
    }

    fun choose(position: Int) {
        cells[position].filled = true
        lock()
    }

}