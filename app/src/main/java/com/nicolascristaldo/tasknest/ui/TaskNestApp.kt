package com.nicolascristaldo.tasknest.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.ui.navigation.AppDestinations
import com.nicolascristaldo.tasknest.ui.navigation.TaskNestNavHost
import com.nicolascristaldo.tasknest.ui.navigation.components.TaskNestFAB
import com.nicolascristaldo.tasknest.ui.navigation.components.TaskNestTopAppBar

@Composable
fun TaskNestApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = when (backStackEntry?.destination?.route) {
        AppDestinations.TaskForm.route -> AppDestinations.TaskForm
        AppDestinations.TaskDetails.route -> AppDestinations.TaskDetails
        else -> AppDestinations.Home
    }

    Scaffold(
        topBar = {
            TaskNestTopAppBar(
                title = when(currentScreen) {
                    is AppDestinations.TaskForm -> {
                        val taskId = backStackEntry?.arguments?.getInt("taskId")
                        val action = if (taskId == 0) stringResource(R.string.create)
                            else stringResource(R.string.edit)
                        stringResource(currentScreen.title, action)
                    }
                    else -> stringResource(currentScreen.title)
                },
                canNavigateBack = currentScreen != AppDestinations.Home,
                navigateUp = { navController.navigateUp() }
            )
        },
        floatingActionButton = {
            if (currentScreen == AppDestinations.Home) {
                TaskNestFAB(
                    onClick = { navController.navigate(AppDestinations.TaskForm.createRoute(0)) },
                    icon = Icons.Filled.Add,
                    contentDescription = R.string.add_task
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Surface {
            TaskNestNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}