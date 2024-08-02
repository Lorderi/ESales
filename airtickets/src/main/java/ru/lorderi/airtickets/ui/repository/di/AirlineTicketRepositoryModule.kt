package ru.lorderi.airtickets.ui.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.lorderi.airtickets.ui.repository.AirlineTicketRepository
import ru.lorderi.airtickets.ui.repository.NetworkAitTicketRepository

@InstallIn(ViewModelComponent::class)
@Module
interface AirlineTicketRepositoryModule {
    @Binds
    fun bindsAirlineTicketRepository(impl: NetworkAitTicketRepository): AirlineTicketRepository
}