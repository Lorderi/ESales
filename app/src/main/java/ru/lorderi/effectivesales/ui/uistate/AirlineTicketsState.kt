package ru.lorderi.effectivesales.ui.uistate

import ru.lorderi.effectivesales.ui.data.Offers
import ru.lorderi.effectivesales.ui.data.SearchOffers

data class AirlineTicketsState(
    val offers: Offers = Offers(emptyList()),
    val searchOffer: SearchOffers = SearchOffers(emptyList()),
)
