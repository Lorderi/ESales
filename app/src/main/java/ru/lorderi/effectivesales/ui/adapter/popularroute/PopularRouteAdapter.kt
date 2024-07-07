package ru.lorderi.effectivesales.ui.adapter.popularroute


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.lorderi.effectivesales.databinding.PopularRouteCardBinding
import ru.lorderi.effectivesales.ui.Constants.colorsOfSearchOffers
import ru.lorderi.effectivesales.ui.data.TicketsOffer

class PopularRouteAdapter :
    ListAdapter<TicketsOffer, PopularRouteViewHolder>(PopularRouteDiffCallback()) {
    private var counter = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularRouteViewHolder {
        if (counter == 4) {
            counter = 1
        }
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PopularRouteCardBinding.inflate(layoutInflater, parent, false)

        val viewHolder = PopularRouteViewHolder(binding, colorsOfSearchOffers[counter++])

        return viewHolder
    }

    override fun onBindViewHolder(holder: PopularRouteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}