package com.coronado.tecsupfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.coronado.tecsupfit.ui.TECSUPFitApp
import com.coronado.tecsupfit.ui.theme.TECSUPFitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TECSUPFitTheme {
                TECSUPFitApp()
            }
        }
    }
}