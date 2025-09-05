package com.nicolascristaldo.tasknest.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        getTasksUseCase(
            name = _uiState.value.nameFilter
        ).map { tasks ->
            HomeUiState(
                tasks = tasks,
                isLoading = false,
                nameFilter = _uiState.value.nameFilter
            )
        }.catch { e ->
            emit(
                HomeUiState(
                    error = e.message ?: "Unknown error",
                    isLoading = false,
                    nameFilter = _uiState.value.nameFilter
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
                nameFilter = value,
                isLoading = true
            )
        }
        loadTasks()
    }
}