package com.youxiang8727.streamletmultiplatform.data.di

import com.youxiang8727.streamletmultiplatform.AppViewModel
import com.youxiang8727.streamletmultiplatform.ui.category.list.CategoriesScreenViewModel
import com.youxiang8727.streamletmultiplatform.ui.home.HomeScreenViewModel
import com.youxiang8727.streamletmultiplatform.ui.settings.SettingsScreenViewModel
import com.youxiang8727.streamletmultiplatform.ui.transaction.TransactionScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<AppViewModel> {
        AppViewModel(get(), get())
    }

    viewModel<HomeScreenViewModel> {
        HomeScreenViewModel(get())
    }

    viewModel<TransactionScreenViewModel> { params ->
        TransactionScreenViewModel(
            get(),
            get(),
            get(),
            params[0]
        )
    }

    viewModel<SettingsScreenViewModel> {
        SettingsScreenViewModel(get(), get())
    }

    viewModel<CategoriesScreenViewModel> {
        CategoriesScreenViewModel(get())
    }
}