package com.nicolascristaldo.tasknest.ui.screens.home

import com.nicolascristaldo.tasknest.domain.model.Task

/**
 * UI state for the Home screen.
 * @param tasks List of tasks to display.
 * @param isLoading Indicates if data is being loaded.
 * @param error Optional error message.
 * @param nameFilter Optional name filter.
 */
data class HomeUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val nameFilter: String? = null
)