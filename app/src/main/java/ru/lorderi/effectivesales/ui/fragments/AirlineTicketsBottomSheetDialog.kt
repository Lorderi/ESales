package ru.lorderi.effectivesales.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.lorderi.effectivesales.databinding.AirlineTicketsBottomSheetBinding
import ru.lorderi.effectivesales.ui.adapter.searchoffers.SearchOffersAdapter
import ru.lorderi.effectivesales.ui.fragments.AirlineTicketsFragment.Companion.CITY_FROM
import ru.lorderi.effectivesales.ui.fragments.AirlineTicketsFragment.Companion.CITY_TO
import ru.lorderi.effectivesales.ui.itemdecoration.OffsetDecoration
import ru.lorderi.effectivesales.ui.repository.TestAitTicketRepository
import ru.lorderi.effectivesales.ui.viewmodel.AirlineTicketsViewModel


class AirlineTicketsBottomSheetDialog : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = AirlineTicketsBottomSheetBinding.inflate(inflater, container, false)

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

        binding.routeBackground.setOnClickListener {
            dismiss()
        }

        binding.globeBackground.setOnClickListener {
            binding.cityTo.setText("Куда угодно")
        }

        binding.calendarBackground.setOnClickListener {
            dismiss()
        }

        binding.fireBackground.setOnClickListener {
            dismiss()
        }

        cityTo?.let {
            binding.cityTo.setText(cityTo)
        }

        cityFrom?.let {
            binding.cityFrom.setText(cityFrom)
        }

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach {
                adapter.submitList(it.searchOffer.searchOffer)
            }
            .launchIn(lifecycleScope)

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    companion object {
        const val TAG = "AirlineTicketsBottomSheetDialog"
    }
}
