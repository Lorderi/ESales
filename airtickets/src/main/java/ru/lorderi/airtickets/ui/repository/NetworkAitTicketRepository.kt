package ru.lorderi.airtickets.ui.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.lorderi.airtickets.api.AirTicketsApi
import ru.lorderi.airtickets.ui.data.Offers
import ru.lorderi.airtickets.ui.data.SearchOffer
import ru.lorderi.airtickets.ui.data.SearchOffers
import ru.lorderi.airtickets.ui.data.Tickets
import ru.lorderi.airtickets.ui.data.TicketsOffers
import javax.inject.Inject

class NetworkAitTicketRepository @Inject constructor(private val api: AirTicketsApi) :
    AirlineTicketRepository {

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

    override fun getSearchOffers(): Flow<SearchOffers> = searchState.asStateFlow()
    override suspend fun getTickets(): Offers = api.getOffers()
    override suspend fun getTicketsOffers(): TicketsOffers = api.getTicketsOffers()
    override suspend fun getTicketsList(): Tickets = api.getTickets()
}
