package ru.lorderi.effectivesales.ui.repository

import kotlinx.coroutines.flow.Flow
import ru.lorderi.effectivesales.ui.data.Offers
import ru.lorderi.effectivesales.ui.data.SearchOffers

interface AirlineTicketRepository {
    fun getTickets(): Flow<Offers>
    fun getSearchOffers(): Flow<SearchOffers>

}