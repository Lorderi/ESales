package ru.lorderi.effectivesales.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.json.Json
import ru.lorderi.effectivesales.R
import ru.lorderi.effectivesales.databinding.FragmentAirlineTicketsListBinding
import ru.lorderi.effectivesales.ui.adapter.searchoffers.AirlineTicketsListAdapter
import ru.lorderi.effectivesales.ui.data.Tickets
import ru.lorderi.effectivesales.ui.itemdecoration.OffsetDecoration
import ru.lorderi.effectivesales.ui.repository.TestAitTicketRepository
import ru.lorderi.effectivesales.ui.viewmodel.AirlineTicketsViewModel


class AirlineTicketsListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAirlineTicketsListBinding.inflate(inflater, container, false)

        val viewModel by viewModels<AirlineTicketsViewModel> {
            viewModelFactory {
                initializer {
                    AirlineTicketsViewModel(TestAitTicketRepository())
                }
            }
        }

        val adapter = AirlineTicketsListAdapter()

        binding.ticketList.adapter = adapter

        bind(binding)

        val text = resources.openRawResource(R.raw.tickets)
            .bufferedReader().use { it.readText() }

        val tickets = if (text.isNotBlank()) {
            Json.decodeFromString(text)
        } else {
            Tickets(emptyList())
        }

        viewModel.setTicketsList(tickets)

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