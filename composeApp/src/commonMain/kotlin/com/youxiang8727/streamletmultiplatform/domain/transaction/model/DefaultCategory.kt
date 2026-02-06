package com.youxiang8727.streamletmultiplatform.domain.transaction.model

import org.jetbrains.compose.resources.StringResource
import streamletmultiplatform.composeapp.generated.resources.Res
import streamletmultiplatform.composeapp.generated.resources.category_expense_communication
import streamletmultiplatform.composeapp.generated.resources.category_expense_daily
import streamletmultiplatform.composeapp.generated.resources.category_expense_education
import streamletmultiplatform.composeapp.generated.resources.category_expense_entertainment
import streamletmultiplatform.composeapp.generated.resources.category_expense_food
import streamletmultiplatform.composeapp.generated.resources.category_expense_insurance
import streamletmultiplatform.composeapp.generated.resources.category_expense_medical
import streamletmultiplatform.composeapp.generated.resources.category_expense_other
import streamletmultiplatform.composeapp.generated.resources.category_expense_tax
import streamletmultiplatform.composeapp.generated.resources.category_expense_transport
import streamletmultiplatform.composeapp.generated.resources.category_expense_utilities
import streamletmultiplatform.composeapp.generated.resources.category_income_allowance
import streamletmultiplatform.composeapp.generated.resources.category_income_bonus
import streamletmultiplatform.composeapp.generated.resources.category_income_investment
import streamletmultiplatform.composeapp.generated.resources.category_income_other
import streamletmultiplatform.composeapp.generated.resources.category_income_salary

enum class DefaultCategory(
    val id: Long,
    val transactionType: TransactionType,
    val stringResource: StringResource
) {
    INCOME_SALARY(
        id = 1,
        transactionType = TransactionType.INCOME,
        stringResource = Res.string.category_income_salary
    ),
    INCOME_BONUS(
        id = 3,
        transactionType = TransactionType.INCOME,
        stringResource = Res.string.category_income_bonus
    ),
    INCOME_INVESTMENT(
        id = 5,
        transactionType = TransactionType.INCOME,
        stringResource = Res.string.category_income_investment
    ),
    INCOME_ALLOWANCE(
        id = 7,
        transactionType = TransactionType.INCOME,
        stringResource = Res.string.category_income_allowance
    ),
    INCOME_OTHER(
        id = 9,
        transactionType = TransactionType.INCOME,
        stringResource = Res.string.category_income_other
    ),
    EXPENSE_FOOD(
        id = 2,
        transactionType = TransactionType.EXPENSE,
        stringResource = Res.string.category_expense_food
    ),
    EXPENSE_TRANSPORT(
        id = 4,
        transactionType = TransactionType.EXPENSE,
        stringResource = Res.string.category_expense_transport
    ),
    EXPENSE_ENTERTAINMENT(
        id = 6,
        transactionType = TransactionType.EXPENSE,
        stringResource = Res.string.category_expense_entertainment
    ),
    EXPENSE_DAILY(
        id = 8,
        transactionType = TransactionType.EXPENSE,
        stringResource = Res.string.category_expense_daily
    ),
    EXPENSE_UTILITIES(
        id = 10,
        transactionType = TransactionType.EXPENSE,
        stringResource = Res.string.category_expense_utilities
    ),
    EXPENSE_INSURANCE(
        id = 12,
        transactionType = TransactionType.EXPENSE,
        stringResource = Res.string.category_expense_insurance
    ),
    EXPENSE_MEDICAL(
        id = 14,
        transactionType = TransactionType.EXPENSE,
        stringResource = Res.string.category_expense_medical
    ),
    EXPENSE_EDUCATION(
        id = 16,
        transactionType = TransactionType.EXPENSE,
        stringResource = Res.string.category_expense_education
    ),
    EXPENSE_COMMUNICATION(
        id = 18,
        transactionType = TransactionType.EXPENSE,
        stringResource = Res.string.category_expense_communication
    ),
    EXPENSE_TAX(
        id = 20,
        transactionType = TransactionType.EXPENSE,
        stringResource = Res.string.category_expense_tax
    ),
    EXPENSE_OTHER(
        id = 22,
        transactionType = TransactionType.EXPENSE,
        stringResource = Res.string.category_expense_other
    ),
}

fun DefaultCategory.toCategory() = Category(
    id = id,
    name = name,
    type = transactionType,
    default = true
)

fun getDefaultCategory(transactionType: TransactionType): DefaultCategory = DefaultCategory.entries.first {
    it.transactionType == transactionType
}