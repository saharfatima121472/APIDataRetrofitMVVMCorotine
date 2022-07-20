package com.example.apidatapractise.container

import com.example.apidatapractise.viewModel.MainActivityViewModel
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@OptIn(KoinApiExtension::class)
class Component:KoinComponent {

   // val mainViewModel : MainActivityViewModel by inject()
 //  private val userViewModel by viewModel<MainActivityViewModel>()
}