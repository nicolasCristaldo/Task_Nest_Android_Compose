package com.nicolascristaldo.tasknest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nicolascristaldo.tasknest.ui.TaskNestApp
import com.nicolascristaldo.tasknest.ui.theme.TaskNestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskNestTheme {
                TaskNestApp()
            }
        }
    }
}