package com.youxiang8727.streamletmultiplatform.data.di

import com.youxiang8727.streamletmultiplatform.data.db.StreamletDatabase
import com.youxiang8727.streamletmultiplatform.data.db.StreamletDatabaseFactory
import com.youxiang8727.streamletmultiplatform.data.db.dao.CategoryEntityDao
import com.youxiang8727.streamletmultiplatform.data.db.dao.TransactionEntityDao
import com.youxiang8727.streamletmultiplatform.data.db.repository.CategoryRepositoryImpl
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.CategoryRepository
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.GetAllCategoriesUseCase
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.InsertDefaultCategoriesUseCase
import org.koin.dsl.module

val databaseModule = module {
    single<StreamletDatabase> {
        StreamletDatabaseFactory().create()
    }

    single<TransactionEntityDao> {
        get<StreamletDatabase>().transactionEntityDao()
    }

    single<CategoryEntityDao> {
        get<StreamletDatabase>().categoryEntityDao()
    }

    single<CategoryRepository> {
        CategoryRepositoryImpl(get())
    }

    factory<InsertDefaultCategoriesUseCase> {
        InsertDefaultCategoriesUseCase(get())
    }

    factory<GetAllCategoriesUseCase> {
        GetAllCategoriesUseCase(get())
    }
}