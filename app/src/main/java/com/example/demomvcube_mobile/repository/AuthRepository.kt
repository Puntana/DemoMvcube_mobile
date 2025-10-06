// ui/auth/repository/AuthRepository.kt
package com.example.demomvcube_mobile.ui.auth.repository

import com.example.demomvcube_mobile.model.auth.RegisterModel
import kotlinx.coroutines.delay

class AuthRepository {

    fun login(email: String, password: String): Boolean {
        // Mock OIDC login
        return (email == "user@example.com" && password == "123456")
    }

    // Mock register function
    suspend fun register(user: RegisterModel): Boolean {
        delay(2000) // simulate network/API
        return (user.password == user.confirmPassword && user.termsAccepted)
    }
}
