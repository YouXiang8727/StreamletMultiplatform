package com.youxiang8727.streamletmultiplatform.domain.transaction.model

import org.jetbrains.compose.resources.StringResource
import streamletmultiplatform.composeapp.generated.resources.Res
import streamletmultiplatform.composeapp.generated.resources.transaction_type_expense
import streamletmultiplatform.composeapp.generated.resources.transaction_type_income

val defaultTransactionType: TransactionType = TransactionType.EXPENSE

enum class TransactionType(val stringResource: StringResource) {
    EXPENSE(stringResource = Res.string.transaction_type_expense),
    INCOME(stringResource = Res.string.transaction_type_income)
}