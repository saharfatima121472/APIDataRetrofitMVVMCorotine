package com.example.apidatapractise.Dependency

import com.example.apidatapractise.network.createNetworkApi
import com.example.apidatapractise.network.createOkHttpClient
import com.example.apidatapractise.network.getRetroInstance
import com.example.apidatapractise.viewModel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val retrofitBuilderModule = module{


    single{ createOkHttpClient()}
    single { getRetroInstance(get()) }
    single { createNetworkApi(get()) }
    viewModel {  MainActivityViewModel(get()) }
}
