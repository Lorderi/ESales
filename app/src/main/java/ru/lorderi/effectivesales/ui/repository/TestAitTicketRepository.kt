package ru.lorderi.effectivesales.ui.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.lorderi.effectivesales.ui.data.Offer
import ru.lorderi.effectivesales.ui.data.Offers
import ru.lorderi.effectivesales.ui.data.Price

class TestAitTicketRepository : AirlineTicketRepository {
    private val state = MutableStateFlow(
        Offers(
            listOf(
                Offer(
                    id = 1,
                    title = "Die Antwoord",
                    town = "Будапешт",
                    price = Price(value = 5000)
                ),
                Offer(
                    id = 2,
                    title = "Socrat&Lera",
                    town = "Санкт-Петербург",
                    price = Price(value = 1999)
                ),
                Offer(
                    id = 3,
                    title = "Лампабикт",
                    town = "Москва",
                    price = Price(value = 2390)
                )
            )
        )
    )

    override fun getTickets(): Flow<Offers> = state.asStateFlow()
}