package ru.lorderi.effectivesales.ui.data

data class Price(
    val value: Int
)

data class Offer(
    val id: Long = 0L,
    val title: String,
    val town: String,
    val price: Price
)

data class Offers(
    val offer: List<Offer>
)
