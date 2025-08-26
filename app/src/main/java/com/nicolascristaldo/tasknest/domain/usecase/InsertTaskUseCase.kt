package com.nicolascristaldo.tasknest.domain.usecase

import com.nicolascristaldo.tasknest.data.repository.TaskRepository
import com.nicolascristaldo.tasknest.domain.model.Task
import javax.inject.Inject

/**
 * Use case for inserting a task into the database.
 */
class InsertTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    /**
     * Inserts a task into the database.
     * @param task The task to insert.
     */
    suspend operator fun invoke(task: Task) = repository.insert(task)
}