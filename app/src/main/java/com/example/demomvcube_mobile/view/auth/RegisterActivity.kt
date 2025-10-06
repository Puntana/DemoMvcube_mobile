package com.example.demomvcube_mobile.ui.auth.view

import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.demomvcube_mobile.R
import com.example.demomvcube_mobile.ui.auth.repository.AuthRepository
import com.example.demomvcube_mobile.ui.auth.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModels {
        object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return RegisterViewModel(AuthRepository()) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)
        val togglePasswordButton = findViewById<ImageButton>(R.id.togglePasswordVisibility)
        val toggleConfirmPasswordButton = findViewById<ImageButton>(R.id.toggleConfirmPasswordVisibility)
        val termsCheckBox = findViewById<CheckBox>(R.id.termsCheckBox)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val backToLoginText = findViewById<TextView>(R.id.backToLoginText)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val passwordStrengthText = findViewById<TextView>(R.id.passwordStrengthText)

        // Observe ViewModel
        viewModel.loading.observe(this, Observer { progressBar.visibility = if (it) ProgressBar.VISIBLE else ProgressBar.GONE })
        viewModel.registerResult.observe(this, Observer { success ->
            if (success) Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
        })
        viewModel.passwordStrength.observe(this, Observer { passwordStrengthText.text = it })

        // Text change
        passwordEditText.addTextChangedListener { viewModel.password.value = it.toString() }
        emailEditText.addTextChangedListener { viewModel.email.value = it.toString() }
        confirmPasswordEditText.addTextChangedListener { viewModel.confirmPassword.value = it.toString() }
        termsCheckBox.setOnCheckedChangeListener { _, checked -> viewModel.termsAccepted.value = checked }

        // Toggle password visibility
        var isPasswordVisible = false
        togglePasswordButton.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            passwordEditText.inputType = if (isPasswordVisible) InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            togglePasswordButton.setImageResource(if (isPasswordVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24)
            passwordEditText.setSelection(passwordEditText.text.length)
        }

        var isConfirmPasswordVisible = false
        toggleConfirmPasswordButton.setOnClickListener {
            isConfirmPasswordVisible = !isConfirmPasswordVisible
            confirmPasswordEditText.inputType = if (isConfirmPasswordVisible) InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            toggleConfirmPasswordButton.setImageResource(if (isConfirmPasswordVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24)
            confirmPasswordEditText.setSelection(confirmPasswordEditText.text.length)
        }

        // Button click
        registerButton.setOnClickListener { viewModel.register() }
        backToLoginText.setOnClickListener { finish() }
    }
}
