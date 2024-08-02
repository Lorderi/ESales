package ru.lorderi.airtickets.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
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
import ru.lorderi.airtickets.ui.util.CyrillicInputFilter
import ru.lorderi.airtickets.ui.viewmodel.AirlineTicketsViewModel


@AndroidEntryPoint
class AirlineTicketsFragment : Fragment() {
    companion object {
        const val AIR_TICKETS = "airTickets"
        const val CITY_TO = "cityTo"
        const val CITY_FROM = "cityFrom"
        fun newInstance() = AirlineTicketsFragment()
    }

    private lateinit var binding: FragmentAirlineTicketsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAirlineTicketsBinding.inflate(inflater, container, false)

        val adapter = AirlineTicketsAdapter()

        val viewModel by viewModels<AirlineTicketsViewModel>()

        binding.cityTo.setOnClickListener {
            findNavController().navigate(
                R.id.action_airlineTicketsFragment_to_airlineTicketsBottomSheetDialog,
                bundleOf(
                    CITY_TO to binding.cityTo.text.toString().trim(),
                    CITY_FROM to binding.cityFrom.text.toString().trim()
                )
            )
        }

        binding.musicList.adapter = adapter

        binding.musicList.addItemDecoration(OffsetDecoration(8, 50, 0, 120))

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach { adapter.submitList(it.offers.offer) }
            .launchIn(lifecycleScope)


        binding.cityFrom.filters = arrayOf(CyrillicInputFilter())
        binding.cityTo.filters = arrayOf(CyrillicInputFilter())


        val sharedPref = requireActivity().getSharedPreferences(AIR_TICKETS, Context.MODE_PRIVATE)
        val cityTo = sharedPref.getString(CITY_TO, "")
        println(cityTo)
        val cityFrom = sharedPref.getString(CITY_FROM, "")
        binding.cityFrom.setText(cityFrom)
        binding.cityTo.setText(cityTo)

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        val sharedPref = requireActivity().getSharedPreferences(AIR_TICKETS, Context.MODE_PRIVATE)
        sharedPref.edit {
            putString(CITY_TO, binding.cityTo.text.toString())
            println("ASDASDASDASD ${binding.cityTo.text}")
            putString(CITY_FROM, binding.cityFrom.text.toString())
        }
    }
}
