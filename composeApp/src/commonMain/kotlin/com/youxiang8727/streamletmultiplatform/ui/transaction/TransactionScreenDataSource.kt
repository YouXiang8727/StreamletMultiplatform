package com.youxiang8727.streamletmultiplatform.ui.transaction

import kotlinx.datetime.LocalDate

sealed class TransactionScreenDataSource {
    data class EditTransactionDataById(val id: Long): TransactionScreenDataSource()

    data class CreateNewTransactionData(val date: LocalDate): TransactionScreenDataSource()
}