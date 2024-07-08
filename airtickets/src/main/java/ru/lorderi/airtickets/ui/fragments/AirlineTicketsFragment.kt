package ru.lorderi.airtickets.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.lorderi.airtickets.R
import ru.lorderi.airtickets.databinding.FragmentAirlineTicketsBinding
import ru.lorderi.airtickets.ui.adapter.airlinetickets.AirlineTicketsAdapter
import ru.lorderi.airtickets.ui.itemdecoration.OffsetDecoration
import ru.lorderi.airtickets.ui.viewmodel.AirlineTicketsViewModel

@AndroidEntryPoint
class AirlineTicketsFragment : Fragment() {
    companion object {
        const val CITY_TO = "cityTo"
        const val CITY_FROM = "cityFrom"
        fun newInstance() = AirlineTicketsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAirlineTicketsBinding.inflate(inflater, container, false)

        val adapter = AirlineTicketsAdapter()

        val viewModel by viewModels<AirlineTicketsViewModel>()

        binding.cityTo.setOnClickListener {
            findNavController().navigate(
                R.id.action_airlineTicketsFragment_to_airlineTicketsBottomSheetDialog,
                bundleOf(
                    CITY_TO to binding.cityTo.text.toString(),
                    CITY_FROM to binding.cityFrom.text.toString()
                )
            )
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
