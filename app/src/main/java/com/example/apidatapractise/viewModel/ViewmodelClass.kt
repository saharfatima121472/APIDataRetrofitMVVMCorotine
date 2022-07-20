package com.example.apidatapractise.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apidatapractise.Model.UserList
import com.example.apidatapractise.network.RetroService
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainActivityViewModel constructor(private val retroService: RetroService) : ViewModel() {

    lateinit var recyclerListData: MutableLiveData<UserList>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getUserListObserverable(): MutableLiveData<UserList> {
        return recyclerListData
    }

    fun getUsersList() {
        val job = viewModelScope.launch(Dispatchers.IO) {
            val response = retroService.getUsersList()
            withContext(Dispatchers.IO) {
                recyclerListData.postValue(response)

            }
        }
    }

    fun searchUser(searchText: String) {
        val job = viewModelScope.launch(Dispatchers.IO) {
            val response = retroService.searchUsers(searchText)
            withContext(Dispatchers.IO) {
                recyclerListData.postValue(response)
            }
        }

    }
}