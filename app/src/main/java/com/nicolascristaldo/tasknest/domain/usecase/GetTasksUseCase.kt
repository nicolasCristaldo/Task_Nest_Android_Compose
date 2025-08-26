package com.nicolascristaldo.tasknest.domain.usecase

import com.nicolascristaldo.tasknest.domain.model.Category
import com.nicolascristaldo.tasknest.domain.model.Status
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
     * @param category Optional [Category] to filter tasks.
     * @param status Optional [Status] to filter tasks.
     * @return A [Flow] emitting a list of [Task] objects matching the criteria, or all tasks if no criteria are provided.
     */
    operator fun invoke(
        name: String? = null,
        category: Category? = null,
        status: Status? = null
    ): Flow<List<Task>> = when {
        name != null -> taskRepository.getTasksByName(name)
        category != null -> taskRepository.getTasksByCategory(category)
        status != null -> taskRepository.getTasksByStatus(status)
        else -> taskRepository.getAllTasks()
    }
}