package com.nicolascristaldo.tasknest.ui.screens.form

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.domain.usecase.GetTaskByIdUseCase
import com.nicolascristaldo.tasknest.domain.usecase.InsertTaskUseCase
import com.nicolascristaldo.tasknest.domain.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the TaskFormScreen.
 * @property context The application context.
 * @property getTaskByIdUseCase Use case for getting a task by its ID.
 * @property insertTaskUseCase Use case for inserting a new task.
 * @property updateTaskUseCase Use case for updating an existing task.
 */
@HiltViewModel
class FormViewmodel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {
    private var _uiState: MutableStateFlow<FormUiState> = MutableStateFlow(FormUiState())
    val uiState get() = _uiState.asStateFlow()

    /**
     * Retrieves a task by its ID and updates the UI state accordingly.
     * @param id The ID of the task to retrieve.
     */
    fun getTask(id: Int) = viewModelScope.launch {
        getTaskByIdUseCase(id)
            .filterNotNull()
            .first()
            .let { task ->
                _uiState.update { currentState -> currentState.copy(taskDetails = task.toTaskDetails()) }
            }
    }

    /**
     * Updates the UI state with the provided task details.
     * @param taskDetails The updated task details.
     */
    fun updateUiState(taskDetails: TaskDetails) {
        _uiState.update { currentState -> currentState.copy(taskDetails = taskDetails) }
    }

    /**
     * Saves a new task if the form is valid.
     */
    fun saveTask() = viewModelScope.launch {
        if (_uiState.value.isEntryValid()) {
            try {
                insertTaskUseCase(_uiState.value.taskDetails.toTask())
            }
            catch (e: Exception) {
                _uiState.update { currentState -> currentState.copy(error = context.getString(R.string.save_task_error, e.message)) }
            }
        }
    }

    /**
     * Updates an existing task if the form is valid.
     */
    fun updateTask() = viewModelScope.launch {
        if (_uiState.value.isEntryValid()) {
            try {
                updateTaskUseCase(_uiState.value.taskDetails.toTask())
            }
            catch (e: Exception) {
                _uiState.update { currentState -> currentState.copy(error = context.getString(R.string.update_task_error, e.message)) }
            }
        }
    }
}