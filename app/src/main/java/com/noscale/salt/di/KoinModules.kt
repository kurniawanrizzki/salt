package com.noscale.salt.di

import com.noscale.salt.data.network.ApiClient
import com.noscale.salt.data.repository.IRepository
import com.noscale.salt.data.repository.Repository
import com.noscale.salt.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModules = module {
    single { ApiClient().httpClient }
}

val repositoryModules = module {
    single<IRepository> { Repository(get()) }
}

val viewModelModules = module {
    viewModel { LoginViewModel(get()) }
}