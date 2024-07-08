package ru.lorderi.airtickets.ui.adapter.popularroute

import androidx.recyclerview.widget.RecyclerView
import ru.lorderi.airtickets.databinding.PopularRouteCardBinding
import ru.lorderi.airtickets.ui.data.TicketsOffer
import ru.lorderi.airtickets.ui.util.getPrice

class PopularRouteViewHolder(
    private val binding: PopularRouteCardBinding,
    private val color: Int
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(ticketsOffer: TicketsOffer) {
        binding.title.text = ticketsOffer.title
        "${ticketsOffer.price.value.getPrice()} ₽".also { binding.price.text = it }

        val time = StringBuilder()
        ticketsOffer.timeRange.forEach { timeRange ->
            time.append("$timeRange ")
        }
        binding.time.text = time.toString()
        binding.routeIcon.setImageResource(color)

    }
}