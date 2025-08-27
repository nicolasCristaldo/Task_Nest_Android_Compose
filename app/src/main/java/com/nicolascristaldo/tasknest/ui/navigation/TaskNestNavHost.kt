package com.nicolascristaldo.tasknest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
                onNavigateToEditTask = {
                    navController.navigate(AppDestinations.TaskForm.createRoute(1))
                },
                onNavigateToTaskDetails = { taskId ->
                    navController.navigate(AppDestinations.TaskDetails.createRoute(taskId))
                }
            )
        }

        composable(
            route = AppDestinations.TaskForm.route,
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
            TaskFormScreen(
                text = if (taskId == 0) "Create Task" else "Edit Task"
            )
        }

        composable(route = AppDestinations.TaskDetails.route) {
            TaskDetailsScreen()
        }
    }
}