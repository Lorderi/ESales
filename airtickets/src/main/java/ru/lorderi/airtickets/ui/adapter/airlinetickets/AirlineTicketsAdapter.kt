package ru.lorderi.airtickets.ui.adapter.airlinetickets


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.lorderi.airtickets.databinding.TicketCardBinding
import ru.lorderi.airtickets.ui.data.Offer

class AirlineTicketsAdapter :
    ListAdapter<Offer, AirlineTicketsViewHolder>(AirlineTicketsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirlineTicketsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TicketCardBinding.inflate(layoutInflater, parent, false)

        val viewHolder = AirlineTicketsViewHolder(binding)

        return viewHolder
    }

    override fun onBindViewHolder(holder: AirlineTicketsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}