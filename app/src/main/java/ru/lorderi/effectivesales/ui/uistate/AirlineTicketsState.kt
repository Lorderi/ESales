package ru.lorderi.effectivesales.ui.uistate

import ru.lorderi.effectivesales.ui.data.Offers

data class AirlineTicketsState(
    val offers: Offers = Offers(emptyList()),
)
