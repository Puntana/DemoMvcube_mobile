// ui/auth/viewmodel/RegisterViewModel.kt
package com.example.demomvcube_mobile.ui.auth.viewmodel

import androidx.lifecycle.*
import com.example.demomvcube_mobile.model.RegisterModel
import com.example.demomvcube_mobile.ui.auth.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {

    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")
    val termsAccepted = MutableLiveData(false)

    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult: LiveData<Boolean> get() = _registerResult

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    // Password strength
    val passwordStrength = MediatorLiveData<String>().apply {
        addSource(password) { value = calculateStrength(it) }
    }

    private fun calculateStrength(pw: String): String {
        return when {
            pw.length >= 12 -> "Strong"
            pw.length >= 8 -> "Medium"
            pw.isEmpty() -> ""
            else -> "Weak"
        }
    }

    fun register() {
        val user = RegisterModel(
            email.value.orEmpty(),
            password.value.orEmpty(),
            confirmPassword.value.orEmpty(),
            termsAccepted.value ?: false
        )

        if (user.email.isEmpty() || user.password.isEmpty() || user.confirmPassword.isEmpty()) return
        if (!user.termsAccepted) return

        _loading.value = true
        viewModelScope.launch {
            val result = repository.register(user)
            _registerResult.value = result
            _loading.value = false
        }
    }
}
