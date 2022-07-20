package com.example.apidatapractise.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apidatapractise.Model.UserList
import com.example.apidatapractise.network.RetroInstance
import com.example.apidatapractise.network.RetroService
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {


    lateinit var recyclerListData: MutableLiveData<UserList>
    val errormeg = MutableLiveData<String>()
    var handlejob: Job? = null
    var handlejob2: Job? = null

    val exceptionalHandler =
        CoroutineExceptionHandler { _, throwable -> onError("Exception : ${throwable.localizedMessage}") }

    val loading = MutableLiveData<Boolean>()

    private fun onError(s: String) {
        errormeg.value = s
        loading.value = false
    }

    init {
        recyclerListData = MutableLiveData()

    }

    override fun onCleared() {
        super.onCleared()
        handlejob?.cancel()
    }


    fun getUserListObserverable(): MutableLiveData<UserList> {
        return recyclerListData
    }

    fun getUsersList() {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)

        val job =  viewModelScope.launch(Dispatchers.IO ){
        val response = retroInstance.getUsersList()


            if(response is UserList) {

            }


            withContext(Dispatchers.IO) {

                    recyclerListData.postValue(response)

            }


//        response.enqueue(object : Callback<UserList> {
//            override fun onFailure(call: Call<UserList>, t: Throwable) {
//                println("error")
//                onError("Error:${t.message}")
//                recyclerListData.postValue(null)
//            }
//
//            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
//                println("success")
//                if (response.isSuccessful) {
//                    recyclerListData.postValue(response.body())
//                } else {
//                    recyclerListData.postValue(null)
//                }
//            }
//        })
    }



//        val call = retroInstance.getUsersList()
//        call.enqueue(object : Callback<UserList>{
//            override fun onFailure(call: Call<UserList>, t: Throwable) {
//                println("error")
//                recyclerListData.postValue(null)
//            }
//
//            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
//                println("success")
//                if(response.isSuccessful) {
//                    recyclerListData.postValue(response.body())
//                } else {
//                    recyclerListData.postValue(null)
//                }
//            }
//        })
    }

    fun searchUser(searchText: String) {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.searchUsers(searchText)
        call.enqueue(object : Callback<UserList> {
            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }
        })
    }
}