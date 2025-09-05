package com.nicolascristaldo.tasknest.domain.usecase

import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving tasks.
 */
class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    /**
     * Retrieves tasks filtered by the provided criteria.
     * @param name Optional text to search for in task names.
     * @return A [Flow] emitting a list of [Task] objects matching the criteria, or all tasks if no criteria are provided.
     */
    operator fun invoke(name: String? = null): Flow<List<Task>> = when {
        name != null -> taskRepository.getTasksByName(name)
        else -> taskRepository.getAllTasks()
    }
}