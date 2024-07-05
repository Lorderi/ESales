package ru.lorderi.effectivesales.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import ru.lorderi.effectivesales.ui.repository.AirlineTicketRepository
import ru.lorderi.effectivesales.ui.uistate.AirlineTicketsState

class AirlineTicketsViewModel(
    private val repository: AirlineTicketRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AirlineTicketsState())
    val uiState = _uiState.asStateFlow()


    init {
        repository.getTickets()
            .onEach { offers ->
                _uiState.update {
                    it.copy(offers = offers)
                }
            }
            .launchIn(viewModelScope)

        repository.getSearchOffers()
            .onEach { searchOffer ->
                _uiState.update {
                    it.copy(searchOffer = searchOffer)
                }
            }
            .launchIn(viewModelScope)
    }
}