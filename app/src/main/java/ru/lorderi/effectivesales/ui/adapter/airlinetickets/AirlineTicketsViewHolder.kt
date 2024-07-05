package ru.lorderi.effectivesales.ui.adapter.airlinetickets

import androidx.recyclerview.widget.RecyclerView
import ru.lorderi.effectivesales.databinding.TicketCardBinding
import ru.lorderi.effectivesales.ui.Constants.offersImages
import ru.lorderi.effectivesales.ui.data.Offer
import ru.lorderi.effectivesales.util.getPrice

class AirlineTicketsViewHolder(
    private val binding: TicketCardBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(offer: Offer) {
        binding.artistName.text = offer.title
        binding.city.text = offer.town
        "от ${offer.price.value.getPrice()} ₽".also { binding.price.text = it }
        binding.ticketAvatar.setImageResource(offersImages[offer.id])
    }
}