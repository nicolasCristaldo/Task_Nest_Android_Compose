package com.nicolascristaldo.tasknest.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.ui.screens.details.TaskDetailsScreen
import com.nicolascristaldo.tasknest.ui.screens.form.TaskFormScreen
import com.nicolascristaldo.tasknest.ui.screens.home.HomeScreen

@Composable
fun TaskNestNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.Home.route,
        modifier = modifier
    ) {
        composable(route = AppDestinations.Home.route) {
            HomeScreen(
                onNavigateToTaskDetails = { taskId ->
                    navController.navigate(AppDestinations.TaskDetails.createRoute(taskId))
                },
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.medium_padding))
                    .fillMaxSize()
            )
        }

        composable(
            route = AppDestinations.TaskForm.route,
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0

            TaskFormScreen(
                id = taskId,
                onNavigateBack = { navController.navigateUp() },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(
            route = AppDestinations.TaskDetails.route,
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0

            TaskDetailsScreen(
                id = taskId,
                onNavigateToTaskForm = { navController.navigate(AppDestinations.TaskForm.createRoute(taskId)) },
                onNavigateBack = { navController.navigateUp() },
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.medium_padding))
                    .fillMaxSize()
            )
        }
    }
}