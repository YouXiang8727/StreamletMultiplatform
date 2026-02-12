package com.youxiang8727.streamletmultiplatform

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.youxiang8727.streamletmultiplatform.data.di.databaseModule
import com.youxiang8727.streamletmultiplatform.data.di.viewModelModule
import com.youxiang8727.streamletmultiplatform.ui.theme.AppTheme
import com.youxiang8727.streamletmultiplatform.ui.navigation.MainNavigation
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(
                databaseModule,
                viewModelModule
            )
        }
    ) {
        AppTheme {
            MainNavigation(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}