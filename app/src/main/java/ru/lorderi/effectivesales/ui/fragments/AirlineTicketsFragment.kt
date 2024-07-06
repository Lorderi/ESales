package ru.lorderi.effectivesales.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.lorderi.effectivesales.R
import ru.lorderi.effectivesales.databinding.FragmentAirlineTicketsBinding
import ru.lorderi.effectivesales.ui.adapter.airlinetickets.AirlineTicketsAdapter
import ru.lorderi.effectivesales.ui.itemdecoration.OffsetDecoration
import ru.lorderi.effectivesales.ui.repository.TestAitTicketRepository
import ru.lorderi.effectivesales.ui.viewmodel.AirlineTicketsViewModel


class AirlineTicketsFragment : Fragment() {
    companion object {
        const val CITY_TO = "cityTo"
        const val CITY_FROM = "cityFrom"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAirlineTicketsBinding.inflate(inflater, container, false)

        val adapter = AirlineTicketsAdapter()

        val viewModel by viewModels<AirlineTicketsViewModel> {
            viewModelFactory {
                initializer {
                    AirlineTicketsViewModel(TestAitTicketRepository())
                }
            }
        }

        binding.cityTo.setOnClickListener {
            findNavController().navigate(
                R.id.action_airlineTicketsFragment_to_airlineTicketsBottomSheetDialog,
                bundleOf(
                    CITY_TO to binding.cityTo.text.toString(),
                    CITY_FROM to binding.cityFrom.text.toString()
                )
            )
//            val modal = AirlineTicketsBottomSheetDialog()
//            modal.arguments =
//                bundleOf(
//                    CITY_TO to binding.cityTo.text.toString(),
//                    CITY_FROM to binding.cityFrom.text.toString()
//                )
//            childFragmentManager.let { modal.show(it, AirlineTicketsBottomSheetDialog.TAG) }
        }

        binding.musicList.adapter = adapter

        binding.musicList.addItemDecoration(OffsetDecoration(8, 67))



        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach { adapter.submitList(it.offers.offer) }
            .launchIn(lifecycleScope)

        return binding.root
    }
}
