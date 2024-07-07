package ru.lorderi.airtickets.ui.repository

import kotlinx.coroutines.flow.Flow
import ru.lorderi.airtickets.ui.data.Offers
import ru.lorderi.airtickets.ui.data.SearchOffers
import ru.lorderi.airtickets.ui.data.Tickets
import ru.lorderi.airtickets.ui.data.TicketsOffers

interface AirlineTicketRepository {
    fun getTickets(): Flow<Offers>
    fun getSearchOffers(): Flow<SearchOffers>
    fun getTicketsOffers(): Flow<TicketsOffers>
    fun getTicketsList(): Flow<Tickets>
    fun setTicketsList(tickets: Tickets)
}