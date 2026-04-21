package com.jujus.vitalix.features.home.di

import com.jujus.vitalix.core.di.AppContainer
import com.jujus.vitalix.features.home.presentation.viewmodels.HomeViewModelFactory

class HomeModule (private val appContainer: AppContainer){



    fun provideHomeViewModelFactory(): HomeViewModelFactory {
        return HomeViewModelFactory()
    }
}