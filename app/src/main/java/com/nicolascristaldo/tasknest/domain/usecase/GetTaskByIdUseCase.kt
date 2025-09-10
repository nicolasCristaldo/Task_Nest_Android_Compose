package com.nicolascristaldo.tasknest.domain.usecase

import com.nicolascristaldo.tasknest.domain.repository.TaskRepository
import javax.inject.Inject

/**
 * Use case for getting a task by its id.
 * @param repository The task repository.
 */
class GetTaskByIdUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    /**
     * Retrieves a task by its id.
     * @param id The id of the task.
     * @return A [Flow] emitting the task.
     */
    operator fun invoke(id: Int) = repository.getTaskById(id)
}