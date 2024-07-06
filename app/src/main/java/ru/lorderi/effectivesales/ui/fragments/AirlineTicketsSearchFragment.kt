package ru.lorderi.effectivesales.ui.fragments

import android.app.DatePickerDialog
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
import ru.lorderi.effectivesales.R
import ru.lorderi.effectivesales.databinding.FragmentAirlineTicketsSearchBinding
import ru.lorderi.effectivesales.ui.adapter.popularroute.PopularRouteAdapter
import ru.lorderi.effectivesales.ui.fragments.AirlineTicketsFragment.Companion.CITY_FROM
import ru.lorderi.effectivesales.ui.fragments.AirlineTicketsFragment.Companion.CITY_TO
import ru.lorderi.effectivesales.ui.itemdecoration.OffsetDecoration
import ru.lorderi.effectivesales.ui.repository.TestAitTicketRepository
import ru.lorderi.effectivesales.ui.viewmodel.AirlineTicketsViewModel
import ru.lorderi.effectivesales.util.getDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AirlineTicketsSearchFragment : Fragment() {
    private val calendar = Calendar.getInstance()
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

        cityTo?.let {
            binding.cityTo.setText(cityTo)
        }

        cityFrom?.let {
            binding.cityFrom.setText(cityFrom)
        }

        val adapter = PopularRouteAdapter()

        binding.cityList.adapter = adapter

        bind(binding)

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach {
                adapter.submitList(it.ticketsOffers.ticketsOffer)
            }
            .launchIn(lifecycleScope)

        return binding.root
    }

    private fun bind(binding: FragmentAirlineTicketsSearchBinding) {
        binding.move.setOnClickListener {
            val from = binding.cityFrom.text
            val to = binding.cityTo.text
            binding.cityFrom.text = to
            binding.cityTo.text = from
        }

        binding.currentDate.text = calendar.getDate()

        binding.forward.setOnClickListener {
            showDatePicker(binding)
        }

        binding.currentDate.setOnClickListener {
            showDatePicker(binding)
        }

        binding.cityList.addItemDecoration(OffsetDecoration(16, 16))

        binding.cancel.setOnClickListener {
            binding.cityTo.setText("")
        }

        binding.escape.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.showAllTickets.setOnClickListener {
            findNavController()
                .navigate(R.id.action_airlineTicketsSearchFragment_to_airlineTicketsListFragment)
        }
    }

    private fun showDatePicker(binding: FragmentAirlineTicketsSearchBinding) {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            requireContext(), { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                // Format the selected date into a string
                binding.currentDate.text = selectedDate.getDate()
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
//                tvSelectedDate.text = "Selected Date: $formattedDate"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }
}
