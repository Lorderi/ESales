package ru.lorderi.airtickets.ui.util

import android.text.InputFilter
import android.text.Spanned

class CyrillicInputFilter : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        if (source == null) return null

        for (i in start until end) {
            if (!source[i].toString().matches(Regex("[а-яА-ЯёЁ -]"))) {
                return ""
            }
        }
        return null
    }
}