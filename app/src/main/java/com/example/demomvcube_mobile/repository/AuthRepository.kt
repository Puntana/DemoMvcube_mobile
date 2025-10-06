package com.example.demomvcube_mobile.ui.auth.repository

class AuthRepository {

    fun login(email: String, password: String): Boolean {
        // Mock OIDC login
        return (email == "user@example.com" && password == "123456")
    }
}
