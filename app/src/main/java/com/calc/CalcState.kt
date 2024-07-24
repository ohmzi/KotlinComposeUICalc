package com.calc

data class CalcState (
    val number1: String = "",
    val number2: String = "",
    val operation: CalcOperation? = null
)