package ru.lorderi.effectivesales.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Calendar.getDate(): String {
    val formatter = SimpleDateFormat("dd MMM, E", Locale("ru"))
    return formatter.format(this.time)
}
