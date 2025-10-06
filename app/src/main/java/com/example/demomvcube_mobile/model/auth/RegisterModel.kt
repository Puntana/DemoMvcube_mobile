// model/RegisterModel.kt
package com.example.demomvcube_mobile.model

data class RegisterModel(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val termsAccepted: Boolean
)
