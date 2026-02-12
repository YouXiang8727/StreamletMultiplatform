package com.youxiang8727.streamletmultiplatform

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.youxiang8727.streamletmultiplatform.data.di.dataStoreModule
import com.youxiang8727.streamletmultiplatform.data.di.databaseModule
import com.youxiang8727.streamletmultiplatform.data.di.viewModelModule
import com.youxiang8727.streamletmultiplatform.ui.navigation.MainNavigation
import com.youxiang8727.streamletmultiplatform.ui.theme.AppTheme
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(
                databaseModule,
                dataStoreModule,
                viewModelModule
            )
        }
    ) {
        val viewModel: AppViewModel = koinViewModel()

        val appThemeMode by viewModel.appThemeMode.collectAsState()

        AppTheme(
            darkTheme = appThemeMode.isSystemInDarkTheme()
        ) {
            MainNavigation(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}