package com.calc

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalcViewModel : ViewModel() {
    fun onAction(calcActions: CalcActions) {
        when (calcActions) {
            is CalcActions.Number -> enterNumber(calcActions.number)
            is CalcActions.Decimal -> enterDecimal()
            is CalcActions.Clear -> state = CalcState()
            is CalcActions.Operation -> enterOperation(calcActions.operation)
            is CalcActions.Calculate -> performCalculation()
            is CalcActions.Delete -> performDeletion()

        }
    }

    private fun performDeletion() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )

            state.operation != null -> state = state.copy(
                operation = null
            )

            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result = when (state.operation) {
                CalcOperation.Add -> number1 + number2
                CalcOperation.Divide -> number1 / number2
                CalcOperation.Multiply -> number1 * number2
                CalcOperation.Subtract -> number1 - number2
                null -> return
                else -> {}
            }
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null
            )
        }
    }

    private fun enterOperation(operation: CalcOperation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal() {
        when {
            state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank() -> state =
                state.copy(
                    number1 = state.number1 + "."
                )

            !state.number2.contains(".") && state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2 + "."
            )
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if (state.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if (state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    var state by mutableStateOf(CalcState())
        private set

    companion object

    val MAX_NUM_LENGTH = 8

}