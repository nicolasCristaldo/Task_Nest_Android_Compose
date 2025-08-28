package com.nicolascristaldo.tasknest.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.tasknest.domain.model.Category
import com.nicolascristaldo.tasknest.domain.model.Status
import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.domain.usecase.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * ViewModel for the Home screen.
 * @param getTasksUseCase Use case for retrieving tasks.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState get() = _uiState.asStateFlow()

    init {
        loadTasks()
    }

    /**
     * Loads tasks based on the current filter.
     */
    private fun loadTasks() {
        val filter = _uiState.value.filter

        getTasksUseCase(
            name = (filter as? Filter.NameFilter)?.value,
            category = (filter as? Filter.CategoryFilter)?.value,
            status = (filter as? Filter.StatusFilter)?.value
        ).map { tasks ->
            HomeUiState(
                tasks = tasks,
                isLoading = false,
                filter = filter
            )
        }.catch { e ->
            emit(
                HomeUiState(
                    error = e.message ?: "Unknown error",
                    isLoading = false,
                    filter = filter
                )
            )
        }.onEach { state ->
            _uiState.update { state }
        }.launchIn(viewModelScope)
    }

    /**
     * Sets the name filter for tasks and loads tasks.
     * @param value The name to filter tasks by.
     */
    fun setNameFilter(value: String?) {
        _uiState.update {
            it.copy(
                filter = Filter.NameFilter(value ?: ""),
                isLoading = true
            )
        }
        loadTasks()
    }

    /**
     * Sets the category filter for tasks and loads tasks.
     * @param value The [Category] to filter tasks by.
     */
    fun setCategoryFilter(value: Category?) {
        _uiState.update {
            it.copy(
                filter = Filter.CategoryFilter(value ?: Category.OTHER),
                isLoading = true
            )
        }
        loadTasks()
    }

    /**
     * Sets the status filter for tasks and loads tasks.
     * @param value The [Status] to filter tasks by.
     */
    fun setStatusFilter(value: Status?) {
        _uiState.update {
            it.copy(
                filter = Filter.StatusFilter(value ?: Status.PENDING),
                isLoading = true
            )
        }
        loadTasks()
    }

    /**
     * Clears the current filter and loads all tasks.
     */
    fun clearFilter() {
        _uiState.update {
            it.copy(
                filter = null,
                isLoading = true
            )
        }
        loadTasks()
    }
}