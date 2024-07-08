package ru.lorderi.airtickets.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.lorderi.airtickets.ui.repository.AirlineTicketRepository
import ru.lorderi.airtickets.ui.uistate.AirlineTicketsState
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AirlineTicketsViewModel @Inject constructor(
    private val repository: AirlineTicketRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AirlineTicketsState())
    val uiState = _uiState.asStateFlow()


    init {
        loadOffers()
        loadTickets()
        loadTicketsList()
        loadSearchOffers()
    }

    fun loadTickets() {
        viewModelScope.launch {
            try {
                val tickets = repository.getTickets()
                _uiState.update {
                    it.copy(offers = tickets)
                }
            } catch (e: Exception) {

            }
        }
    }

    fun loadSearchOffers() {
        repository.getSearchOffers()
            .onEach { searchOffer ->
                _uiState.update {
                    it.copy(searchOffer = searchOffer)
                }
            }
            .launchIn(viewModelScope)
    }

    fun loadTicketsList() {
        viewModelScope.launch {
            try {
                val ticketsList = repository.getTicketsList()
                _uiState.update {
                    it.copy(ticketsList = ticketsList)
                }
            } catch (e: Exception) {

            }
        }
    }

    fun loadOffers() {
        viewModelScope.launch {
            try {
                val ticketsOffers = repository.getTicketsOffers()
                _uiState.update {
                    it.copy(ticketsOffers = ticketsOffers)
                }
            } catch (e: Exception) {

            }
        }
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