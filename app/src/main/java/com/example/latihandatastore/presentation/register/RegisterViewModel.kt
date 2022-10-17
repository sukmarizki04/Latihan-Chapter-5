package com.example.latihandatastore.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.latihandatastore.data.DataStoreManager
import kotlinx.coroutines.launch

class RegisterViewModel(private val dataStoreManager: DataStoreManager): ViewModel() {

    fun saveAccount(username: String, password: String) {
        viewModelScope.launch {
            dataStoreManager.setUsername(username)
            dataStoreManager.setPassword(password)
        }
    }

}