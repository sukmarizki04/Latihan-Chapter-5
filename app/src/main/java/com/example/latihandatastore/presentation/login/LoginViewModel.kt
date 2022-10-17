package com.example.latihandatastore.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.latihandatastore.data.DataStoreManager
import kotlinx.coroutines.launch

class LoginViewModel(private val dataStoreManager: DataStoreManager): ViewModel() {

    fun statusLogin(isLogin: Boolean) {
        viewModelScope.launch {
            dataStoreManager.statusLogin(isLogin)
        }
    }

    fun getUsername(): LiveData<String> {
        return dataStoreManager.getUsername().asLiveData()
    }

    fun getPassword(): LiveData<String> {
        return dataStoreManager.getPassword().asLiveData()
    }

    fun getLoginStatus(): LiveData<Boolean> {
        return dataStoreManager.getLoginStatus().asLiveData()
    }

}