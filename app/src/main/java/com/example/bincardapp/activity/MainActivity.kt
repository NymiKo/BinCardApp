package com.example.bincardapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bincardapp.core.ui_style.theme.BINCardAppTheme
import com.example.bincardapp.features.bin_lookup.ui.BinLookupScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BINCardAppTheme {
                BinLookupScreen()
            }
        }
    }
}