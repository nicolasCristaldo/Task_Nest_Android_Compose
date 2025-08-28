package com.nicolascristaldo.tasknest.ui.screens.home

import com.nicolascristaldo.tasknest.domain.model.Category
import com.nicolascristaldo.tasknest.domain.model.Status
import com.nicolascristaldo.tasknest.domain.model.Task

/**
 * UI state for the Home screen.
 * @param tasks List of tasks to display.
 * @param isLoading Indicates if data is being loaded.
 * @param error Optional error message.
 * @param filter Optional filter criteria.
 */
data class HomeUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val filter: Filter? = null
)

/**
 * Filter options for the Home screen.
 */
sealed class Filter {
    data class NameFilter(val value: String) : Filter()
    data class CategoryFilter(val value: Category) : Filter()
    data class StatusFilter(val value: Status) : Filter()
}