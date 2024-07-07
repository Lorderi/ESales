package ru.lorderi.effectivesales.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import ru.lorderi.effectivesales.ui.data.Tickets
import ru.lorderi.effectivesales.ui.repository.AirlineTicketRepository
import ru.lorderi.effectivesales.ui.uistate.AirlineTicketsState
import java.util.Calendar

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

        repository.getTicketsOffers()
            .onEach { ticketsOffers ->
                _uiState.update {
                    it.copy(ticketsOffers = ticketsOffers)
                }
            }
            .launchIn(viewModelScope)

        repository.getTicketsList()
            .onEach { ticketsList ->
                _uiState.update {
                    it.copy(ticketsList = ticketsList)
                }
            }
            .launchIn(viewModelScope)

    }

    fun setTicketsList(tickets: Tickets) {
        repository.setTicketsList(tickets)
    }

    fun setPassengerCounter(count: Int) {
        _uiState.update {
            it.copy(counterPassenger = count)
        }
    }

    fun getPassengerCounter() = _uiState.value.counterPassenger
    fun setCurrentDate(date: Calendar) {
        _uiState.update {
            it.copy(currentDate = date)
        }
    }

    fun getCurrentDate() = _uiState.value.currentDate
    fun setBackwardDate(date: Calendar) {
        _uiState.update {
            it.copy(backDate = date)
        }
    }

    fun getBackwardDate() = _uiState.value.backDate
}