package ru.lorderi.effectivesales.ui.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tickets(
    @SerialName("tickets")
    val tickets: List<Ticket>
)

@Serializable
data class Ticket(
    @SerialName("id")
    val id: Int,
    @SerialName("badge")
    val badge: String? = null,
    @SerialName("price")
    val price: Price,
    @SerialName("provider_name")
    val providerName: String,
    @SerialName("company")
    val company: String,
    @SerialName("departure")
    val departure: Departure,
    @SerialName("arrival")
    val arrival: Arrival,
    @SerialName("has_transfer")
    val hasTransfer: Boolean,
    @SerialName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    @SerialName("luggage")
    val luggage: Luggage,
    @SerialName("hand_luggage")
    val handLuggage: HandLuggage,
    @SerialName("is_returnable")
    val isReturnable: Boolean,
    @SerialName("is_exchangable")
    val isExchangable: Boolean,
)

@Serializable
data class Departure(
    @SerialName("town")
    val town: String,
    @SerialName("date")
    val date: String,
    @SerialName("airport")
    val airport: String,
)

@Serializable
data class Arrival(
    @SerialName("town")
    val town: String,
    @SerialName("date")
    val date: String,
    @SerialName("airport")
    val airport: String,
)

@Serializable
data class Luggage(
    @SerialName("has_luggage")
    val hasLuggage: Boolean,
    @SerialName("price")
    val price: Price? = null
)

@Serializable
data class HandLuggage(
    @SerialName("has_hand_luggage")
    val hasHandLuggage: Boolean,
    @SerialName("size")
    val size: String? = null
)



