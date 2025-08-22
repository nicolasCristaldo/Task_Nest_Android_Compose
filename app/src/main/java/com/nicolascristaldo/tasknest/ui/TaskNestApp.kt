package com.nicolascristaldo.tasknest.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.tasknest.ui.navigation.TaskNestNavHost

@Composable
fun TaskNestApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    Scaffold { innerPadding ->
        Surface {
            TaskNestNavHost(
                navController = navController,
                modifier = modifier.padding(innerPadding)
            )
        }
    }
}