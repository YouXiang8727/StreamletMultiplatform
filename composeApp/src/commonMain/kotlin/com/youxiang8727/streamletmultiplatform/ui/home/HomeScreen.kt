package com.youxiang8727.streamletmultiplatform.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.youxiang8727.streamletmultiplatform.core.ui.UiText
import com.youxiang8727.streamletmultiplatform.ui.common.calendar.BasicCalendarView
import com.youxiang8727.streamletmultiplatform.ui.common.calendar.rememberCalendarState
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import streamletmultiplatform.composeapp.generated.resources.Res
import streamletmultiplatform.composeapp.generated.resources.label_expense
import streamletmultiplatform.composeapp.generated.resources.label_income

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    HomeScreenContent(
        modifier = modifier,
        uiState = state,
        onDateSelected = {
            viewModel.onDateSelected(it)
        }
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    uiState: HomeScreenUiState = HomeScreenUiState(),
    onDateSelected: (LocalDate) -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        val calendarState = rememberCalendarState(
            onDateSelected = onDateSelected
        )

        BasicCalendarView(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            state = calendarState,
            header = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier,
                        text = uiState.selectedDateString,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            item {
                TransactionTypeLabel(
                    modifier = Modifier,
                    text = stringResource(
                        Res.string.label_income,
                        uiState.income
                    )
                )
            }

            items(
                items = uiState.incomeTransactions,
                key = { it.id }
            ) {
                TransactionItem(
                    modifier = Modifier.fillMaxWidth(),
                    transactionItemUiState = it
                )
            }

            item {
                TransactionTypeLabel(
                    modifier = Modifier,
                    text = stringResource(
                        Res.string.label_expense,
                        uiState.expense
                    )
                )
            }

            items(
                items = uiState.expenseTransactions,
                key = { it.id }
            ) {
                TransactionItem(
                    modifier = Modifier.fillMaxWidth(),
                    transactionItemUiState = it
                )
            }
        }
    }
}

@Composable
private fun TransactionTypeLabel(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.labelLarge,
        text = text
    )
}

@Composable
private fun TransactionItem(
    modifier: Modifier = Modifier,
    transactionItemUiState: TransactionItemUiState = TransactionItemUiState()
) {
    ElevatedCard(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transactionItemUiState.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = transactionItemUiState.categoryUiText.asString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                text = transactionItemUiState.amount.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
@Preview(
    name = "HomeScreenContentPreview",
    showSystemUi = true,
    showBackground = true
)
private fun HomeScreenContentPreview() {
    MaterialTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            HomeScreenContent(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding),
                uiState = HomeScreenUiState(
                    income = 400.0,
                    incomeTransactions = listOf(
                        TransactionItemUiState(
                            id = 0,
                            title = "發票中獎",
                            amount = 200.0,
                            categoryUiText = UiText.DynamicString("獎金")
                        ),
                        TransactionItemUiState(
                            id = 1,
                            title = "發票中獎",
                            amount = 200.0,
                            categoryUiText = UiText.DynamicString("獎金")
                        ),
                    ),
                    expense = 314.0,
                    expenseTransactions = listOf(
                        TransactionItemUiState(
                            id = 2,
                            title = "捷運",
                            amount = 30.0,
                            categoryUiText = UiText.DynamicString("交通")
                        ),
                        TransactionItemUiState(
                            id = 3,
                            title = "早餐",
                            amount = 59.0,
                            categoryUiText = UiText.DynamicString("食物")
                        ),
                        TransactionItemUiState(
                            id = 4,
                            title = "午餐",
                            amount = 125.0,
                            categoryUiText = UiText.DynamicString("食物")
                        ),
                        TransactionItemUiState(
                            id = 5,
                            title = "捷運",
                            amount = 30.0,
                            categoryUiText = UiText.DynamicString("交通")
                        ),
                        TransactionItemUiState(
                            id = 6,
                            title = "飲料",
                            amount = 35.0,
                            categoryUiText = UiText.DynamicString("食物")
                        ),
                        TransactionItemUiState(
                            id = 7,
                            title = "飲料",
                            amount = 35.0,
                            categoryUiText = UiText.DynamicString("食物")
                        ),
                    )
                )
            )
        }
    }
}