package com.calc

sealed class  CalcOperation (val symbol: String){
    object Add : CalcOperation("+")
    object Subtract : CalcOperation("-")
    object Multiply : CalcOperation("*")
    object Divide : CalcOperation("/")
    object Calculate : CalcOperation("=")
    data class Operation(val number: Int) : CalcOperation("")
}