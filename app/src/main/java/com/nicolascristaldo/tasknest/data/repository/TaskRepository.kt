package com.nicolascristaldo.tasknest.data.repository

import com.nicolascristaldo.tasknest.data.mapper.toDomain
import com.nicolascristaldo.tasknest.data.mapper.toDomainList
import com.nicolascristaldo.tasknest.data.mapper.toEntity
import com.nicolascristaldo.tasknest.data.room.dao.TaskDAO
import com.nicolascristaldo.tasknest.domain.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repository class for managing tasks.
 * @property taskDAO The DAO for tasks.
 */
class TaskRepository @Inject constructor(
    private val taskDAO: TaskDAO
) {
    /**
     * Inserts a task into the database.
     * @param task The task to insert.
     */
    suspend fun insert(task: Task) =
        withContext(Dispatchers.IO) { taskDAO.insert(task.toEntity()) }

    /**
     * Updates a task in the database.
     * @param task The task to update.
     */
    suspend fun update(task: Task) =
        withContext(Dispatchers.IO) { taskDAO.update(task.toEntity()) }

    /**
     * Deletes a task from the database.
     * @param task The task to delete.
     */
    suspend fun delete(task: Task) =
        withContext(Dispatchers.IO) { taskDAO.delete(task.toEntity()) }

    /**
     * Retrieves all tasks from the database.
     * @return A [Flow] emitting a list of all tasks.
     */
    fun getAllTasks(): Flow<List<Task>> =
        taskDAO.getAllTasks().map { it.toDomainList() }

    /**
     * Retrieves a task by its id.
     * @param id The id of the task.
     * @return A [Flow] emitting the task.
     */
    fun getTaskById(id: Int): Flow<Task> =
        taskDAO.getTaskById(id).map { it.toDomain() }

    /**
     * Retrieves tasks whose names contain the specified text.
     * @param name The text to search for.
     * @return A [Flow] emitting a list of matching tasks.
     */
    fun getTasksByName(name: String): Flow<List<Task>> =
        taskDAO.getTasksByName(name).map { it.toDomainList() }

    /**
     * Retrieves tasks by their category.
     * @param category The category of the task.
     * @return A [Flow] emitting a list of the tasks with the specified category.
     */
    fun getTasksByCategory(category: String): Flow<List<Task>> =
        taskDAO.getTasksByCategory(category).map { it.toDomainList() }

    /**
     * Retrieves tasks by their status.
     * @param status The status of the task.
     * @return A [Flow] emitting a list of the tasks with the specified status.
     */
    fun getTasksByStatus(status: String): Flow<List<Task>> =
        taskDAO.getTasksByStatus(status).map { it.toDomainList() }
}