package ru.lorderi.effectivesales.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun String.getTime(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    val outputFormat = SimpleDateFormat("HH:mm", Locale.US)
    val date: Date? = inputFormat.parse(this)
    return if (date != null) {
        val formattedDate: String = outputFormat.format(date)
        formattedDate
    } else {
        ""
    }
}

fun String.getTimeRange(date: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)

    val date1 = inputFormat.parse(this)
    val date2 = inputFormat.parse(date)
    return if (date1 != null && date2 != null) {
        val diff = date2.time - date1.time
        val seconds = diff / 1000.0
        val minutes = seconds / 60.0
        val hours = minutes / 60.0
        if (date2.after(date1)) {
            String.format("%.1f", hours)
        } else {
            ""
        }
    } else {
        ""
    }
}
