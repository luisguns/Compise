package br.com.compise.di

import br.com.compise.ui.home.HomeViewModel
import br.com.compise.ui.place.PlaceNameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presenterModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { PlaceNameViewModel() }
}