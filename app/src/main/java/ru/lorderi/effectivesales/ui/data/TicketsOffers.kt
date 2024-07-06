package ru.lorderi.effectivesales.ui.data

data class TicketsOffers(
    val ticketsOffer: List<TicketsOffer>
)

data class TicketsOffer(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Price
)