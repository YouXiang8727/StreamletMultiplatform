package com.youxiang8727.streamletmultiplatform.ui.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.DefaultCategory
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.defaultTransactionType
import com.youxiang8727.streamletmultiplatform.ui.common.calendar.BasicCalendarView
import com.youxiang8727.streamletmultiplatform.ui.common.calendar.rememberCalendarState
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import streamletmultiplatform.composeapp.generated.resources.Res
import streamletmultiplatform.composeapp.generated.resources.button_cancel
import streamletmultiplatform.composeapp.generated.resources.button_save
import streamletmultiplatform.composeapp.generated.resources.label_amount
import streamletmultiplatform.composeapp.generated.resources.label_category
import streamletmultiplatform.composeapp.generated.resources.label_date
import streamletmultiplatform.composeapp.generated.resources.label_description
import streamletmultiplatform.composeapp.generated.resources.label_title

@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier,
    viewModel: TransactionScreenViewModel = koinViewModel() {
        parametersOf(0L)
    },
    back: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TransactionScreenContent(
        modifier = modifier,
        transactionScreenUiState = state,
        onTransactionTypeChanged = viewModel::onTransactionTypeChanged,
        onDateSelected = viewModel::onDateSelected,
        onTitleChanged = viewModel::onTitleChanged,
        onCategoryChanged = viewModel::onCategoryChanged,
        onAmountChanged = viewModel::onAmountChanged,
        onDescriptionChanged = viewModel::onDescriptionChanged,
        onCancel = { back() },
        onSave = viewModel::onSaveClicked
    )
}

@Composable
private fun TransactionScreenContent(
    modifier: Modifier = Modifier,
    transactionScreenUiState: TransactionScreenUiState = TransactionScreenUiState(),
    onTransactionTypeChanged: (TransactionType) -> Unit = {},
    onDateSelected: (LocalDate) -> Unit = {},
    onTitleChanged: (String) -> Unit = {},
    onCategoryChanged: (Category) -> Unit = {},
    onAmountChanged: (String) -> Unit = {},
    onDescriptionChanged: (String) -> Unit = {},
    onCancel: () -> Unit = {},
    onSave: () -> Unit = {}
) {
    var showDateSelector by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TransactionType.entries.forEach { transactionType ->
                FilterChip(
                    selected = transactionScreenUiState.transactionType == transactionType,
                    label = {
                        Text(
                            text = stringResource(transactionType.stringResource),
                            style = MaterialTheme.typography.labelLarge
                        )
                    },
                    onClick = {
                        onTransactionTypeChanged(transactionType)
                    }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LabelText(
                text = stringResource(Res.string.label_date)
            )

            TextButton(
                onClick = {
                    showDateSelector = true
                }
            ) {
                Text(
                    text = transactionScreenUiState.dateString,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = transactionScreenUiState.title,
            onValueChange = {
                onTitleChanged(it)
            },
            label = {
                Text(
                    text = stringResource(Res.string.label_title)
                )
            },
            supportingText = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = "${transactionScreenUiState.title.length}/${TransactionScreenUiState.MAX_TITLE_LENGTH}"
                    )
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Abc,
                    contentDescription = null
                )
            }
        )

        LabelText(
            text = stringResource(Res.string.label_category)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = transactionScreenUiState.categories,
                key = { it.id }
            ) {
                FilterChip(
                    selected = transactionScreenUiState.category == it,
                    label = {
                        Text(
                            text = it.categoryUiText.asString(),
                            style = MaterialTheme.typography.labelLarge
                        )
                    },
                    onClick = {
                        onCategoryChanged(it)
                    }
                )
            }
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = transactionScreenUiState.amountString,
            onValueChange = {
                onAmountChanged(it)
            },
            label = {
                Text(
                    text = stringResource(Res.string.label_amount)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AttachMoney,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = transactionScreenUiState.description,
            onValueChange = {
                onDescriptionChanged(it)
            },
            label = {
                Text(
                    text = stringResource(Res.string.label_description)
                )
            },
            placeholder = {
                Text(
                    text = "0.0"
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.EditNote,
                    contentDescription = null
                )
            },
            supportingText = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = "${transactionScreenUiState.description.length}/${TransactionScreenUiState.MAX_DESCRIPTION_LENGTH}"
                    )
                }
            }
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    onCancel()
                }
            ) {
                Text(
                    text = stringResource(Res.string.button_cancel)
                )
            }

            Button(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                enabled = transactionScreenUiState.saveable,
                onClick = {
                    onSave()
                }
            ) {
                Text(
                    text = stringResource(Res.string.button_save)
                )
            }
        }

        if (showDateSelector) {
            DatePickerBottomSheet(
                modifier = Modifier.fillMaxWidth(),
                date = transactionScreenUiState.date,
                onDateSelected = onDateSelected,
                onDismiss = {
                    showDateSelector = false
                }
            )
        }
    }
}

@Composable
private fun LabelText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerBottomSheet(
    modifier: Modifier = Modifier,
    date: LocalDate,
    onDateSelected: (LocalDate) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    val calendarState = rememberCalendarState(
        initialDate = date,
        onDateSelected = {
            onDateSelected(it)
            onDismiss()
        }
    )

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismiss
    ) {
        BasicCalendarView(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            state = calendarState
        )
    }
}


@Composable
@Preview(
    name = "TransactionScreenContentPreview",
    showSystemUi = true,
    showBackground = true
)
private fun TransactionScreenContentPreview() {
    val categories = DefaultCategory.entries.filter { it.transactionType == defaultTransactionType }.mapIndexed { index, category ->
        Category(
            id = index.toLong(),
            name = category.name,
            type = defaultTransactionType,
            default = true
        )
    }

    val state = TransactionScreenUiState(
        categories = categories
    )
    MaterialTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            TransactionScreenContent(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                transactionScreenUiState = state
            )
        }
    }
}