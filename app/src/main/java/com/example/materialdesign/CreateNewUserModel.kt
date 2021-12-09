package com.example.materialdesign

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateNewUserModel:ViewModel() {
    var createUserLiveData: MutableLiveData<UserResponse?>
    var loadUserData:MutableLiveData<UserResponse?>
    lateinit var deleteUserLivedata:MutableLiveData<UserResponse?>
    init {
        createUserLiveData = MutableLiveData()
        loadUserData = MutableLiveData()
        deleteUserLivedata = MutableLiveData()
    }

    fun getCreateNewUserObservable():MutableLiveData<UserResponse?>{
        return createUserLiveData
    }
    fun getLoadUserObservable():MutableLiveData<UserResponse?>{
        return loadUserData
    }
    fun getDeleteUserObservable():MutableLiveData<UserResponse?>{
        return deleteUserLivedata
    }

    fun createUser(user:User){
        val retroInstance = RetrofitInstance.getRetrofitInstance()
            .create(RetroServices::class.java)
        val call = retroInstance.createUser(user)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    createUserLiveData.postValue(response.body())
                } else {
                    createUserLiveData.postValue(null)
                }
            }
        })
    }
    fun getUserData(user_id:String){
        val retroInstance = RetrofitInstance.getRetrofitInstance()
            .create(RetroServices::class.java)
        val call = retroInstance.getUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                loadUserData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    loadUserData.postValue(response.body())
                } else {
                    loadUserData.postValue(null)
                }
            }
        })
    }
    fun updateUser(user_id: String,user:User){
        val retroInstance = RetrofitInstance.getRetrofitInstance()
            .create(RetroServices::class.java)
        val call = retroInstance.updateUser(user_id,user)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    createUserLiveData.postValue(response.body())
                } else {
                    createUserLiveData.postValue(null)
                }
            }
        })
    }
    fun deleteUser(user_id: String?){
        val retroInstance = RetrofitInstance.getRetrofitInstance()
            .create(RetroServices::class.java)
        val call = retroInstance.deleteUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                deleteUserLivedata.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    deleteUserLivedata.postValue(response.body())
                } else {
                    deleteUserLivedata.postValue(null)
                }
            }
        })
    }
}