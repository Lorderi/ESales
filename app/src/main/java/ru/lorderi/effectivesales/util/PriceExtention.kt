package ru.lorderi.effectivesales.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Int.getPrice(): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault())
    symbols.groupingSeparator = ' '
    val formatter = DecimalFormat("#,###", symbols)

    return formatter.format(this)
}
