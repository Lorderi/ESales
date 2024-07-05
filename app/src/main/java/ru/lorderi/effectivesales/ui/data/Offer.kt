package ru.lorderi.effectivesales.ui.data

data class Price(
    val value: Int
)

data class Offer(
    val id: Int = 0,
    val title: String,
    val town: String,
    val price: Price
)

data class Offers(
    val offer: List<Offer>
)
