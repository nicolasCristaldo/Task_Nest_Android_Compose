package com.nicolascristaldo.tasknest.domain.repository

import com.nicolascristaldo.tasknest.domain.model.Category
import com.nicolascristaldo.tasknest.domain.model.Status
import com.nicolascristaldo.tasknest.domain.model.Task
import kotlinx.coroutines.flow.Flow

/**
 * Interface for a repository that manages tasks.
 */
interface TaskRepository {
    suspend fun insert(task: Task)
    suspend fun update(task: Task)
    suspend fun delete(task: Task)
    fun getAllTasks(): Flow<List<Task>>
    fun getTaskById(id: Int): Flow<Task>
    fun getTasksByName(name: String): Flow<List<Task>>
    fun getTasksByCategory(category: Category): Flow<List<Task>>
    fun getTasksByStatus(status: Status): Flow<List<Task>>
}