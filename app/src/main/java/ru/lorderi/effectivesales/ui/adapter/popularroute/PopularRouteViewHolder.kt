package ru.lorderi.effectivesales.ui.adapter.popularroute

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.lorderi.effectivesales.databinding.PopularRouteCardBinding
import ru.lorderi.effectivesales.ui.data.TicketsOffer
import ru.lorderi.effectivesales.util.getPrice

class PopularRouteViewHolder(
    private val binding: PopularRouteCardBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(ticketsOffer: TicketsOffer) {
        binding.title.text = ticketsOffer.title
        "от ${ticketsOffer.price.value.getPrice()} ₽".also { binding.price.text = it }

        ticketsOffer.timeRange.forEach { timeRange ->
            val textview = TextView(binding.root.context)
            textview.text = timeRange
            binding.time.addView(textview)
        }
    }
}