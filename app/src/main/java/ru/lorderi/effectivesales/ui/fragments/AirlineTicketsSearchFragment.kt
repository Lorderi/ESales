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
import ru.lorderi.effectivesales.databinding.FragmentAirlineTicketsSearchBinding
import ru.lorderi.effectivesales.ui.adapter.searchoffers.SearchOffersAdapter
import ru.lorderi.effectivesales.ui.fragments.AirlineTicketsFragment.Companion.CITY_FROM
import ru.lorderi.effectivesales.ui.fragments.AirlineTicketsFragment.Companion.CITY_TO
import ru.lorderi.effectivesales.ui.itemdecoration.OffsetDecoration
import ru.lorderi.effectivesales.ui.repository.TestAitTicketRepository
import ru.lorderi.effectivesales.ui.viewmodel.AirlineTicketsViewModel


class AirlineTicketsSearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAirlineTicketsSearchBinding.inflate(inflater, container, false)

        val cityTo = arguments?.getString(CITY_TO)
        val cityFrom = arguments?.getString(CITY_FROM)

        val viewModel by viewModels<AirlineTicketsViewModel> {
            viewModelFactory {
                initializer {
                    AirlineTicketsViewModel(TestAitTicketRepository())
                }
            }
        }

        val adapter = SearchOffersAdapter()

        binding.cityList.adapter = adapter

        binding.cityList.addItemDecoration(OffsetDecoration(16, 16))

        binding.cancel.setOnClickListener {
            binding.cityTo.setText("")
        }


        cityTo?.let {
            binding.cityTo.setText(cityTo)
        }

        cityFrom?.let {
            binding.cityFrom.setText(cityFrom)
        }

        binding.escape.setOnClickListener {
            requireParentFragment().findNavController().navigateUp()
        }

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach {
                adapter.submitList(it.searchOffer.searchOffer)
            }
            .launchIn(lifecycleScope)

        return binding.root
    }
}
