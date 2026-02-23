package com.youxiang8727.streamletmultiplatform.ui.category.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import streamletmultiplatform.composeapp.generated.resources.Res
import streamletmultiplatform.composeapp.generated.resources.alert_category_duplicated
import streamletmultiplatform.composeapp.generated.resources.button_cancel
import streamletmultiplatform.composeapp.generated.resources.button_save
import streamletmultiplatform.composeapp.generated.resources.label_category_name

@Composable
fun EditCategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: EditCategoryScreenViewModel = koinViewModel(),
    back: () -> Unit = {}
) {
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is EditCategoryScreenUiEffect.OnDataSaved -> {
                    back()
                }
            }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    EditCategoryScreenContent(
        modifier = modifier,
        uiState = state,
        onTransactionTypeChanged = viewModel::onTransactionTypeChanged,
        onNameChanged = viewModel::onNameChanged,
        saveCategory = viewModel::onSaveClicked,
        back = back
    )

    if (state.showDuplicatedAlertDialog) {
        CategoryDuplicatedAlertDialog(
            modifier = Modifier.fillMaxWidth(),
            onSaveData = viewModel::saveCategory,
            onDismiss = viewModel::dismissCategoryDuplicatedAlertDialog
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(
    name = "EditCategoryScreenContentPreview",
    showSystemUi = true,
    showBackground = true
)
private fun EditCategoryScreenContent(
    modifier: Modifier = Modifier,
    uiState: EditCategoryScreenUiState = EditCategoryScreenUiState(),
    onTransactionTypeChanged: (TransactionType) -> Unit = {},
    onNameChanged: (String) -> Unit = {},
    saveCategory: () -> Unit = {},
    back: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            back()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
                windowInsets = WindowInsets(),
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TransactionType.entries.forEach { transactionType ->
                    FilterChip(
                        selected = uiState.transactionType == transactionType,
                        onClick = {
                            onTransactionTypeChanged(transactionType)
                        },
                        label = {
                            Text(
                                modifier = Modifier,
                                text = stringResource(transactionType.stringResource)
                            )
                        }
                    )
                }
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.name,
                onValueChange = onNameChanged,
                label = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(Res.string.label_category_name)
                    )
                }
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.saveable,
                shape = RoundedCornerShape(4.dp),
                onClick = saveCategory
            ) {
                Text(
                    text = stringResource(Res.string.button_save)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(
    name = "CategoryDuplicatedAlertDialogPreview",
    showSystemUi = true,
    showBackground = true
)
private fun CategoryDuplicatedAlertDialog(
    modifier: Modifier = Modifier,
    onSaveData: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    AlertDialog(
        modifier = modifier,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = true
        ),
        confirmButton = {
            TextButton(
                onClick = {
                    onSaveData()
                }
            ) {
                Text(
                    text = stringResource(Res.string.button_save)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(
                    text = stringResource(Res.string.button_cancel)
                )
            }
        },
        text = {
            Text(
                text = stringResource(Res.string.alert_category_duplicated)
            )
        },
        onDismissRequest = onDismiss
    )
}