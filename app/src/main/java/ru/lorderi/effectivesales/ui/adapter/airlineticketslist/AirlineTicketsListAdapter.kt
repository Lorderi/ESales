package ru.lorderi.effectivesales.ui.adapter.searchoffers


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.lorderi.effectivesales.databinding.TicketAdvancedCardBinding
import ru.lorderi.effectivesales.ui.data.Ticket

class AirlineTicketsListAdapter :
    ListAdapter<Ticket, AirlineTicketsListViewHolder>(AirlineTicketsListDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AirlineTicketsListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TicketAdvancedCardBinding.inflate(layoutInflater, parent, false)

        val viewHolder = AirlineTicketsListViewHolder(binding)

        return viewHolder
    }

    override fun onBindViewHolder(holder: AirlineTicketsListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}