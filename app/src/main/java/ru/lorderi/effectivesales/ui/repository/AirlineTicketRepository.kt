package ru.lorderi.effectivesales.ui.repository

import kotlinx.coroutines.flow.Flow
import ru.lorderi.effectivesales.ui.data.Offers

interface AirlineTicketRepository {
    fun getTickets(): Flow<Offers>

}