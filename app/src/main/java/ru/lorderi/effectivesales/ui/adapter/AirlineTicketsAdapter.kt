package ru.lorderi.effectivesales.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.lorderi.effectivesales.databinding.TicketCardBinding
import ru.lorderi.effectivesales.ui.data.Offer

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