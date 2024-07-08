package ru.lorderi.airtickets.ui.repository

import kotlinx.coroutines.flow.Flow
import ru.lorderi.airtickets.ui.data.Offers
import ru.lorderi.airtickets.ui.data.SearchOffers
import ru.lorderi.airtickets.ui.data.Tickets
import ru.lorderi.airtickets.ui.data.TicketsOffers

interface AirlineTicketRepository {
    suspend fun getTickets(): Offers
    fun getSearchOffers(): Flow<SearchOffers>
    suspend fun getTicketsOffers(): TicketsOffers
    suspend fun getTicketsList(): Tickets
}
