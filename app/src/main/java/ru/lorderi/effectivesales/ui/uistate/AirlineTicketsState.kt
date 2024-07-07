package ru.lorderi.effectivesales.ui.uistate

import ru.lorderi.effectivesales.ui.data.Offers
import ru.lorderi.effectivesales.ui.data.SearchOffers
import ru.lorderi.effectivesales.ui.data.Tickets
import ru.lorderi.effectivesales.ui.data.TicketsOffers
import java.util.Calendar

data class AirlineTicketsState(
    val offers: Offers = Offers(emptyList()),
    val searchOffer: SearchOffers = SearchOffers(emptyList()),
    val ticketsOffers: TicketsOffers = TicketsOffers(emptyList()),
    val ticketsList: Tickets = Tickets(emptyList()),
    val counterPassenger: Int = 1,
    val currentDate: Calendar? = null,
    val backDate: Calendar? = null,
)
