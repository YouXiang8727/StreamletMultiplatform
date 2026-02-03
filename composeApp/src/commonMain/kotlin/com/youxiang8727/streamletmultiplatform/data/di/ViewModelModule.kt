package com.youxiang8727.streamletmultiplatform.data.di

import com.youxiang8727.streamletmultiplatform.AppViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<AppViewModel> {
        AppViewModel(get())
    }
}