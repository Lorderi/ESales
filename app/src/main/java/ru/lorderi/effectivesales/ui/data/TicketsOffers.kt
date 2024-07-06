package ru.lorderi.effectivesales.ui.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsOffers(
    @SerialName("tickets_offers")
    val ticketsOffer: List<TicketsOffer>
)

@Serializable
data class TicketsOffer(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("time_range")
    val timeRange: List<String>,
    @SerialName("price")
    val price: Price
)
