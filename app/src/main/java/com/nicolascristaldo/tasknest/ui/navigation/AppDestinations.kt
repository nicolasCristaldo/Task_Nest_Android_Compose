package com.nicolascristaldo.tasknest.ui.navigation

import com.nicolascristaldo.tasknest.R

/**
 * Defines navigation destinations for the app.
 * @param route The navigation route for the destination.
 * @param title The resource ID for the TopAppBar title.
 */
sealed class AppDestinations(
    val route: String,
    val title: Int
) {
    data object Home : AppDestinations(
        route = "home",
        title = R.string.app_name
    )

    data object TaskForm : AppDestinations(
        route = "task_form/{taskId}",
        title = R.string.task_form
    ) {
        fun createRoute(taskId: Int) = "task_form/$taskId"
    }

    data object TaskDetails : AppDestinations(
        route = "task_details/{taskId}",
        title = R.string.task_details
    ) {
        fun createRoute(taskId: Int) = "task_details/$taskId"
    }
}