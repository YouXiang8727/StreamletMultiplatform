package com.youxiang8727.streamletmultiplatform.ui.category.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.DefaultCategory
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.toCategory
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import streamletmultiplatform.composeapp.generated.resources.Res
import streamletmultiplatform.composeapp.generated.resources.hint_default_category_protected

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoriesScreenViewModel = koinViewModel(),
    navigateToEditCategoryById: (Long) -> Unit = {}
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val defaultCategoryProtectedMessage = stringResource(Res.string.hint_default_category_protected)

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CategoriesScreenUiEffect.NavigateToEditCategoryScreen -> {
                    navigateToEditCategoryById(effect.id)
                }
                is CategoriesScreenUiEffect.ShowDefaultCategoryProtectedMessage -> {
                    snackbarHostState.showSnackbar(
                        message = defaultCategoryProtectedMessage
                    )
                }
            }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    CategoriesScreenContent(
        modifier = modifier,
        uiState = state,
        snackbarHostState = snackbarHostState,
        onTransactionTypeChanged = viewModel::onTransactionTypeChanged,
        onCategoryClicked = viewModel::onCategoryClicked,
        navigateToCreateNewCategory = {
            navigateToEditCategoryById(0L)
        }
    )
}

@Composable
private fun CategoriesScreenContent(
    modifier: Modifier = Modifier,
    uiState: CategoriesScreenUiState = CategoriesScreenUiState(),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    onTransactionTypeChanged: (TransactionType) -> Unit = {},
    onCategoryClicked: (Category) -> Unit = {},
    navigateToCreateNewCategory: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigateToCreateNewCategory()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TransactionType.entries.forEach { type ->
                    FilterChip(
                        selected = uiState.transactionType == type,
                        onClick = {
                            onTransactionTypeChanged(type)
                        },
                        label = {
                            Text(
                                text = stringResource(type.stringResource),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = uiState.categories,
                    key = { it.id }
                ) { category ->
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .clickable {
                                onCategoryClicked(category)
                            },
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = category.categoryUiText.asString(),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(
    name = "CategoriesScreenContentPreview",
    showSystemUi = true,
    showBackground = true
)
private fun CategoriesScreenContentPreview() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        CategoriesScreenContent(
            modifier = Modifier.fillMaxSize()
                .padding(it)
                .padding(16.dp), 
            uiState = CategoriesScreenUiState(
                transactionType = TransactionType.INCOME,
                categories = DefaultCategory.entries.filter {
                    it.transactionType == TransactionType.INCOME
                }.map {
                    it.toCategory()
                }
            )
        )
    }
}