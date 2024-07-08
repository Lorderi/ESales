package ru.lorderi.airtickets.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.lorderi.airtickets.databinding.FragmentAirlineTicketsListBinding
import ru.lorderi.airtickets.ui.adapter.airlineticketslist.AirlineTicketsListAdapter
import ru.lorderi.airtickets.ui.fragments.AirlineTicketsFragment.Companion.CITY_FROM
import ru.lorderi.airtickets.ui.fragments.AirlineTicketsFragment.Companion.CITY_TO
import ru.lorderi.airtickets.ui.fragments.AirlineTicketsSearchFragment.Companion.CURRENT_DATE
import ru.lorderi.airtickets.ui.fragments.AirlineTicketsSearchFragment.Companion.PASSENGER_COUNTER
import ru.lorderi.airtickets.ui.itemdecoration.OffsetDecoration
import ru.lorderi.airtickets.ui.viewmodel.AirlineTicketsViewModel

@AndroidEntryPoint
class AirlineTicketsListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAirlineTicketsListBinding.inflate(inflater, container, false)

        val viewModel by viewModels<AirlineTicketsViewModel>()

        val cityTo = arguments?.getString(CITY_TO)
        val cityFrom = arguments?.getString(CITY_FROM)
        val currentDate = arguments?.getString(CURRENT_DATE)
        val passengerCounter = arguments?.getString(PASSENGER_COUNTER)

        if (cityTo != null && cityFrom != null) {
            "$cityFrom-$cityTo".also { binding.route.text = it }
        }

        "$currentDate, $passengerCounter пассажир".also { binding.dateAndPassengerCount.text = it }


        val adapter = AirlineTicketsListAdapter()

        binding.ticketList.adapter = adapter

        bind(binding)

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach {
                adapter.submitList(it.ticketsList.tickets)
            }
            .launchIn(lifecycleScope)

        return binding.root
    }

    private fun bind(binding: FragmentAirlineTicketsListBinding) {

        binding.ticketList.addItemDecoration(OffsetDecoration(16, 16))

        binding.escape.setOnClickListener {
            requireParentFragment().findNavController().navigateUp()
        }
    }

}
