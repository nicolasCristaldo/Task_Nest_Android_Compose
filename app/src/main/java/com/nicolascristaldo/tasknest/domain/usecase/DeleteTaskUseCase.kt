package com.nicolascristaldo.tasknest.domain.usecase

import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.domain.repository.TaskRepository
import javax.inject.Inject

/**
 * Use case for deleting a task from the database.
 */
class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    /**
     * Deletes a task from the database.
     * @param task The task to delete.
     */
    suspend operator fun invoke(task: Task) = repository.delete(task)
}