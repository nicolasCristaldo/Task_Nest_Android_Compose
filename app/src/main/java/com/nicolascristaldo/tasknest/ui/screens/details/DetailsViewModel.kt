package com.nicolascristaldo.tasknest.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.domain.usecase.DeleteTaskUseCase
import com.nicolascristaldo.tasknest.domain.usecase.GetTaskByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the task details screen.
 * @property getTaskByIdUseCase Use case for getting a task by its id.
 * @property deleteTaskUseCase Use case for deleting a task.
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
    private var _task = MutableStateFlow<Task?>(null)
    val task get() = _task.asStateFlow()

    /**
     * Retrieves a task by its id.
     * @param id The id of the task.
     */
    fun getTask(id: Int) = viewModelScope.launch {
        getTaskByIdUseCase(id).collect { task ->
            _task.value = task
        }
    }

    /**
     * Deletes the current task.
     */
    fun deleteTask() = viewModelScope.launch {
        deleteTaskUseCase(_task.value!!)
    }
}