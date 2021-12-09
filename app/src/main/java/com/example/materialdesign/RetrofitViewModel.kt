package com.example.materialdesign

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitViewModel:ViewModel() {
    var recyclerListData : MutableLiveData<UserList>
    init {
        recyclerListData = MutableLiveData()
    }

    fun getUserListObservalble(): MutableLiveData<UserList>{
        return  recyclerListData
    }

    fun getUserList() {
        val retroInstance = RetrofitInstance.getRetrofitInstance()
            .create(RetroServices::class.java)
        val call = retroInstance.getUserList()
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
    fun searchUser(searchText:String) {
        val retroInstance = RetrofitInstance.getRetrofitInstance()
            .create(RetroServices::class.java)
        val call = retroInstance.searchUser(searchText)
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