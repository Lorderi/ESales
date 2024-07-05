package ru.lorderi.effectivesales.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.lorderi.effectivesales.databinding.TicketCardBinding
import ru.lorderi.effectivesales.ui.data.Offer

class AirlineTicketsViewHolder(
    private val binding: TicketCardBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(offer: Offer) {
        binding.artistName.text = offer.title
        binding.city.text = offer.town
    }
}