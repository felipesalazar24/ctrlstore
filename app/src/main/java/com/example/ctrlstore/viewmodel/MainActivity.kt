package com.example.ctrlstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ctrlstore.ui.theme.CTRLstoreTheme
import com.example.ctrlstore.ui.theme.screen.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CTRLstoreTheme {
                HomeScreen()
            }


        }
    }
}

