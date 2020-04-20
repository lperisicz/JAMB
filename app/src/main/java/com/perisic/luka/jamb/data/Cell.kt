package com.perisic.luka.jamb.data

data class Cell(
    var filled: Boolean = false,
    var value: Int = -1,
    var locked: Boolean = true,
    val validator: Validator,
    val display: Boolean
)