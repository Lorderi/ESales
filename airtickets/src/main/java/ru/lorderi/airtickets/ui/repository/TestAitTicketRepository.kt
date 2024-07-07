package ru.lorderi.airtickets.ui.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.lorderi.airtickets.ui.data.Offer
import ru.lorderi.airtickets.ui.data.Offers
import ru.lorderi.airtickets.ui.data.Price
import ru.lorderi.airtickets.ui.data.SearchOffer
import ru.lorderi.airtickets.ui.data.SearchOffers
import ru.lorderi.airtickets.ui.data.Tickets
import ru.lorderi.airtickets.ui.data.TicketsOffer
import ru.lorderi.airtickets.ui.data.TicketsOffers

class TestAitTicketRepository : AirlineTicketRepository {
    private val offerState = MutableStateFlow(
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

    private val searchState = MutableStateFlow(
        SearchOffers(
            listOf(
                SearchOffer(
                    id = 1,
                    town = "Стамбул",
                ),
                SearchOffer(
                    id = 2,
                    town = "Сочи",
                ),
                SearchOffer(
                    id = 3,
                    town = "Пхукет",
                )
            )
        )
    )

    private val ticketsOffers = MutableStateFlow(
        TicketsOffers(
            listOf(
                TicketsOffer(
                    id = 1,
                    title = "Уральские авиалинии",
                    timeRange = listOf(
                        "07:00",
                        "09:10",
                        "10:00",
                        "11:30",
                        "14:15",
                        "19:10",
                        "21:00",
                        "23:30"
                    ),
                    price = Price(3999)
                ),
                TicketsOffer(
                    id = 10,
                    title = "Победа",
                    timeRange = listOf(
                        "09:10",
                        "21:00"
                    ),
                    price = Price(4999)
                ),
                TicketsOffer(
                    id = 30,
                    title = "NordStar",
                    timeRange = listOf(
                        "07:00",
                    ),
                    price = Price(2390)
                )
            )
        )
    )

    private val ticketsList = MutableStateFlow(
        Tickets(
            emptyList()
        )
    )

    override fun getTickets(): Flow<Offers> = offerState.asStateFlow()
    override fun getSearchOffers(): Flow<SearchOffers> = searchState.asStateFlow()

    override fun getTicketsOffers(): Flow<TicketsOffers> = ticketsOffers.asStateFlow()
    override fun getTicketsList(): Flow<Tickets> = ticketsList.asStateFlow()
    override fun setTicketsList(tickets: Tickets) {
        ticketsList.update {
            tickets
        }
    }
}
