package com.example.demomvcube_mobile.ui.auth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomvcube_mobile.ui.auth.repository.AuthRepository

class LoginViewModel : ViewModel() {

    private val repository = AuthRepository()

    val loginResult = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage.value = "Email and Password cannot be empty"
            return
        }

        val result = repository.login(email, password)
        loginResult.value = result
        if (!result) errorMessage.value = "Invalid credentials"
    }
}
