package com.example.demomvcube_mobile.ui.main

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomAppBar(navController = navController) }) { innerPadding ->}
}