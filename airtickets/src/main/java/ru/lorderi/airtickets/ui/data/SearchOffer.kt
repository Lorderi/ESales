package ru.lorderi.airtickets.ui.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchOffers(
    @SerialName("search_offer")
    val searchOffer: List<SearchOffer>
)

@Serializable
data class SearchOffer(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("town")
    val town: String,
)
