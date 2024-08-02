package ru.lorderi.airtickets.ui.fragments

import android.app.DatePickerDialog
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
import ru.lorderi.airtickets.databinding.FragmentAirlineTicketsSearchBinding
import ru.lorderi.airtickets.ui.adapter.popularroute.PopularRouteAdapter
import ru.lorderi.airtickets.ui.fragments.AirlineTicketsFragment.Companion.AIR_TICKETS
import ru.lorderi.airtickets.ui.fragments.AirlineTicketsFragment.Companion.CITY_FROM
import ru.lorderi.airtickets.ui.fragments.AirlineTicketsFragment.Companion.CITY_TO
import ru.lorderi.airtickets.ui.itemdecoration.OffsetDecoration
import ru.lorderi.airtickets.ui.util.CyrillicInputFilter
import ru.lorderi.airtickets.ui.util.getDate
import ru.lorderi.airtickets.ui.util.getShorterDate
import ru.lorderi.airtickets.ui.viewmodel.AirlineTicketsViewModel
import java.util.Calendar

@AndroidEntryPoint
class AirlineTicketsSearchFragment : Fragment() {
    companion object {
        const val CURRENT_DATE = "currentDate"
        const val PASSENGER_COUNTER = "passengerCounter"
    }

    private lateinit var binding: FragmentAirlineTicketsSearchBinding
    private val calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAirlineTicketsSearchBinding.inflate(inflater, container, false)

        val cityTo = arguments?.getString(CITY_TO)
        val cityFrom = arguments?.getString(CITY_FROM)

        val viewModel by viewModels<AirlineTicketsViewModel>()

        cityTo?.let {
            binding.cityTo.setText(cityTo)
        }

        cityFrom?.let {
            binding.cityFrom.setText(cityFrom)
        }

        val adapter = PopularRouteAdapter()

        binding.cityList.adapter = adapter

        bind(binding, viewModel)

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach {
                adapter.submitList(it.ticketsOffers.ticketsOffer.take(3))
            }
            .launchIn(lifecycleScope)

        return binding.root
    }

    private fun bind(
        binding: FragmentAirlineTicketsSearchBinding,
        viewModel: AirlineTicketsViewModel,
    ) {
        binding.cityFrom.filters = arrayOf(CyrillicInputFilter())
        binding.cityTo.filters = arrayOf(CyrillicInputFilter())

        val passengerCounter = viewModel.getPassengerCounter()
        "$passengerCounter,эконом".also { binding.passengerCounter.text = it }

        binding.move.setOnClickListener {
            val from = binding.cityFrom.text
            val to = binding.cityTo.text
            binding.cityFrom.text = to
            binding.cityTo.text = from
        }

        val currentDate = viewModel.getCurrentDate()

        if (currentDate != null) {
            binding.currentDate.text = currentDate.getDate()
        } else {
            viewModel.setCurrentDate(calendar)
            binding.currentDate.text = calendar.getDate()
        }

        val backDate = viewModel.getBackwardDate()

        if (backDate != null) {
            binding.back.text = backDate.getDate()
        }

        binding.back.setOnClickListener {
            showDatePicker { selectedDate ->
                val date = selectedDate.getDate()
                binding.back.text = date
                viewModel.setBackwardDate(selectedDate)
            }
        }

        binding.currentDate.setOnClickListener {
            showDatePicker { selectedDate ->
                val date = selectedDate.getDate()
                binding.currentDate.text = date
                viewModel.setCurrentDate(selectedDate)
            }
        }

        binding.cityList.addItemDecoration(OffsetDecoration(0, 0, 8, 0))

        binding.cancel.setOnClickListener {
            binding.cityTo.setText("")
        }

        binding.escape.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.showAllTickets.setOnClickListener {
            findNavController()
                .navigate(
                    R.id.action_airlineTicketsSearchFragment_to_airlineTicketsListFragment,
                    bundleOf(
                        CITY_TO to binding.cityTo.text.toString().trim(),
                        CITY_FROM to binding.cityFrom.text.toString().trim(),
                        CURRENT_DATE to viewModel.getCurrentDate()?.getShorterDate()?.trim(),
                        PASSENGER_COUNTER to viewModel.getPassengerCounter().toString().trim()
                    )
                )
        }
    }

    override fun onPause() {
        super.onPause()
        val sharedPref = requireActivity().getSharedPreferences(AIR_TICKETS, Context.MODE_PRIVATE)
        sharedPref.edit {
            putString(CITY_TO, binding.cityTo.text.toString())
            putString(CITY_FROM, binding.cityFrom.text.toString())
        }
    }
    private fun showDatePicker(listener: (date: Calendar) -> Unit) {
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                listener(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}
