package ru.lorderi.airtickets.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.lorderi.airtickets.R
import ru.lorderi.airtickets.databinding.AirlineTicketsBottomSheetBinding
import ru.lorderi.airtickets.ui.adapter.searchoffers.SearchOffersAdapter
import ru.lorderi.airtickets.ui.fragments.AirlineTicketsFragment.Companion.CITY_FROM
import ru.lorderi.airtickets.ui.fragments.AirlineTicketsFragment.Companion.CITY_TO
import ru.lorderi.airtickets.ui.itemdecoration.OffsetDecoration
import ru.lorderi.airtickets.ui.viewmodel.AirlineTicketsViewModel

@AndroidEntryPoint
class AirlineTicketsBottomSheetDialog : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = AirlineTicketsBottomSheetBinding.inflate(inflater, container, false)

        val cityTo = arguments?.getString(CITY_TO)
        val cityFrom = arguments?.getString(CITY_FROM)

        val viewModel by viewModels<AirlineTicketsViewModel>()

        cityTo?.let {
            binding.cityTo.setText(cityTo)
        }

        cityFrom?.let {
            binding.cityFrom.setText(cityFrom)
        }

        val adapter = SearchOffersAdapter { city ->
            binding.cityTo.setText(city)
        }

        binding.cityList.adapter = adapter

        bind(binding)

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach {
                adapter.submitList(it.searchOffer.searchOffer)
            }
            .launchIn(lifecycleScope)

        return binding.root
    }

    private fun bind(
        binding: AirlineTicketsBottomSheetBinding,
    ) {
        binding.cityList.addItemDecoration(OffsetDecoration(0, 0, 8, 0))

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


        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val from = binding.cityFrom.text.toString()
                val to = binding.cityTo.text.toString()

                if (from.isNotBlank() && to.isNotBlank()) {
                    requireParentFragment().findNavController()
                        .navigate(
                            R.id.action_airlineTicketsBottomSheetDialog_to_airlineTicketsSearchFragment,
                            bundleOf(
                                CITY_TO to binding.cityTo.text.toString(),
                                CITY_FROM to binding.cityFrom.text.toString()
                            )
                        )
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }

        binding.cityFrom.addTextChangedListener(textWatcher)
        binding.cityTo.addTextChangedListener(textWatcher)
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
}
