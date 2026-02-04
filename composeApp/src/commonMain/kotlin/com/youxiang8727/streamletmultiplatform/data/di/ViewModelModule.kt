package com.youxiang8727.streamletmultiplatform.data.di

import com.youxiang8727.streamletmultiplatform.AppViewModel
import com.youxiang8727.streamletmultiplatform.ui.home.HomeScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<AppViewModel> {
        AppViewModel(get())
    }

    viewModel<HomeScreenViewModel> {
        HomeScreenViewModel(get())
    }
}