package ru.lorderi.effectivesales.ui.adapter.searchoffers

import androidx.recyclerview.widget.RecyclerView
import ru.lorderi.effectivesales.databinding.TicketAdvancedCardBinding
import ru.lorderi.effectivesales.ui.data.Ticket

class AirlineTicketsListViewHolder(
    private val binding: TicketAdvancedCardBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(ticket: Ticket) {
        binding.price.text = ticket.price.value.toString()
        binding.airportFrom.text = ticket.departure.airport
        binding.airportTo.text = ticket.arrival.airport
        binding.timeFrom.text = ticket.departure.date
        binding.timeTo.text = ticket.arrival.date
    }
}