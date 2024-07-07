package ru.lorderi.airtickets.ui.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Price(
    @SerialName("value")
    val value: Int
)

@Serializable
data class Offer(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("title")
    val title: String,
    @SerialName("town")
    val town: String,
    @SerialName("price")
    val price: Price
)

@Serializable
data class Offers(
    @SerialName("offers")
    val offer: List<Offer>
)
