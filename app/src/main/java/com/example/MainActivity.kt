package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.ui.PortfolioScreen
import com.example.ui.theme.AlexArantesTheme
import com.example.viewmodel.PortfolioViewModel

class MainActivity : ComponentActivity() {
    private val portfolioViewModel: PortfolioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlexArantesTheme {
                PortfolioScreen(viewModel = portfolioViewModel)
            }
        }
    }
}
