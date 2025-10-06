//package com.example.demomvcube_mobile.ui.auth.view

import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.demomvcube_mobile.R
import com.example.demomvcube_mobile.ui.auth.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val showPasswordCheckBox = findViewById<CheckBox>(R.id.showPasswordCheckBox)
        val loginButton = findViewById<Button>(R.id.loginButton)
//        val languageSpinner = findViewById<Spinner>(R.id.languageSpinner)

//        // Language Selector
//        val languages = listOf("English", "ไทย")
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languages)
//        languageSpinner.adapter = adapter

        // Toggle show password
        showPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            passwordEditText.inputType =
                if (isChecked) InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        // Observe ViewModel
        viewModel.loginResult.observe(this, Observer { success ->
            if (success) Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        })

        viewModel.errorMessage.observe(this, Observer { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        })

        // Button click
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            viewModel.login(email, password)
        }
    }
}
