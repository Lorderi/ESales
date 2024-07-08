package ru.lorderi.airtickets.api

import retrofit2.http.GET
import ru.lorderi.airtickets.ui.data.Offers
import ru.lorderi.airtickets.ui.data.Tickets
import ru.lorderi.airtickets.ui.data.TicketsOffers

interface AirTicketsApi {
    @GET("uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav")
    suspend fun getOffers(): Offers

    @GET("uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta")
    suspend fun getTicketsOffers(): TicketsOffers

    @GET("uc?id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    suspend fun getTickets(): Tickets
}
