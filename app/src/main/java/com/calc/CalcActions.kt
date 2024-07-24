package com.calc

sealed class CalcActions {
    data class Number(val number: Int) : CalcActions()
    object Clear : CalcActions()
    object Delete : CalcActions()
    object Decimal : CalcActions()
    object Calculate : CalcActions()
    data class Operation(val operation: CalcOperation) : CalcActions()
}