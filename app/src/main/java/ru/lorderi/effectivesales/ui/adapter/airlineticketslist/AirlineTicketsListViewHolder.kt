package ru.lorderi.effectivesales.ui.adapter.airlineticketslist

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.lorderi.effectivesales.databinding.TicketAdvancedCardBinding
import ru.lorderi.effectivesales.ui.data.Ticket
import ru.lorderi.effectivesales.util.getPrice
import ru.lorderi.effectivesales.util.getTime
import ru.lorderi.effectivesales.util.getTimeRange

class AirlineTicketsListViewHolder(
    private val binding: TicketAdvancedCardBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(ticket: Ticket) {
        if (ticket.badge != null) {
            binding.badge.isVisible = true
            binding.badgeLabel.text = ticket.badge
        } else {
            binding.badge.isVisible = false
        }

        val price = ticket.price.value
        "${price.getPrice()} ₽".also { binding.price.text = it }
        binding.airportFrom.text = ticket.departure.airport
        binding.airportTo.text = ticket.arrival.airport
        binding.timeFrom.text = ticket.departure.date.getTime()
        binding.timeTo.text = ticket.arrival.date.getTime()
        val timeRange = ticket.departure.date.getTimeRange(ticket.arrival.date)

        if (ticket.hasTransfer) {
            binding.hasTransferGroup.isVisible = true
        } else {
            binding.hasTransferGroup.isVisible = true
        }
        "$timeRange ч в пути".also { binding.routeLong.text = it }
    }
}