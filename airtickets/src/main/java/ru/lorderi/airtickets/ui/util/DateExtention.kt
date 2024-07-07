package ru.lorderi.airtickets.ui.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Calendar.getDate(): String {
    val formatter = SimpleDateFormat("dd MMM, E", Locale("ru"))
    return formatter.format(this.time)
}

fun Calendar.getShorterDate(): String {
    val formatter = SimpleDateFormat("dd MMMM", Locale("ru"))
    return formatter.format(this.time)
}
