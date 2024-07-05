package ru.lorderi.effectivesales.ui.data


data class SearchOffers(
    val searchOffer: List<SearchOffer>
)

data class SearchOffer(
    val id: Int = 0,
    val town: String,
)
