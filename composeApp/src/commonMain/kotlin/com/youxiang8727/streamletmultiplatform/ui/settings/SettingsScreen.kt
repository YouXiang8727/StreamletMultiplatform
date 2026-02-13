package com.youxiang8727.streamletmultiplatform.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.youxiang8727.streamletmultiplatform.domain.settings.model.AppThemeMode
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import streamletmultiplatform.composeapp.generated.resources.Res
import streamletmultiplatform.composeapp.generated.resources.label_app_theme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SettingsScreenContent(
        modifier = modifier,
        uiState = state,
        saveAppThemeMode = viewModel::saveAppThemeMode
    )
}

@Composable
private fun SettingsScreenContent(
    modifier: Modifier = Modifier,
    uiState: SettingsScreenUiState = SettingsScreenUiState(),
    saveAppThemeMode: (AppThemeMode) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            SettingsScreenLabelText(
                modifier = Modifier,
                text = stringResource(
                    Res.string.label_app_theme
                )
            )
        }

        item {
            AppThemeModeView(
                modifier = Modifier.fillMaxWidth(),
                appThemeMode = uiState.appThemeMode,
                saveAppThemeMode = saveAppThemeMode
            )
        }
    }
}

@Composable
private fun AppThemeModeView(
    modifier: Modifier = Modifier,
    appThemeMode: AppThemeMode = AppThemeMode.SYSTEM,
    saveAppThemeMode: (AppThemeMode) -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AppThemeMode.entries.forEach { mode ->
            OutlinedCard(
                modifier = Modifier.padding(horizontal = 8.dp),
                border = CardDefaults.outlinedCardBorder(enabled = appThemeMode == mode),
                onClick = {
                    saveAppThemeMode(mode)
                },
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SettingsScreenItemText(
                        text = stringResource(mode.stringResource)
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsScreenLabelText(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelLarge
    )
}

@Composable
private fun SettingsScreenItemText(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
@Preview(
    name = "SettingsScreenContentPreview",
    showBackground = true,
    showSystemUi = true
)
private fun SettingsScreenContentPreview() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        SettingsScreenContent(
            modifier = Modifier.padding(innerPadding)
                .padding(16.dp)
        )
    }
}