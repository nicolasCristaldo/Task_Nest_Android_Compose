package com.nicolascristaldo.tasknest.data.repository

import com.nicolascristaldo.tasknest.data.mapper.toDomain
import com.nicolascristaldo.tasknest.data.mapper.toDomainList
import com.nicolascristaldo.tasknest.data.mapper.toEntity
import com.nicolascristaldo.tasknest.data.room.dao.TaskDAO
import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repository for managing tasks in the database.
 * @property taskDAO The DAO for tasks.
 */
class TaskRepositoryImpl @Inject constructor(
    private val taskDAO: TaskDAO
): TaskRepository {
    /**
     * Inserts a task into the database.
     * @param task The task to insert.
     */
    override suspend fun insert(task: Task) =
        withContext(Dispatchers.IO) { taskDAO.insert(task.toEntity()) }

    /**
     * Updates a task in the database.
     * @param task The task to update.
     */
    override suspend fun update(task: Task) =
        withContext(Dispatchers.IO) { taskDAO.update(task.toEntity()) }

    /**
     * Deletes a task from the database.
     * @param task The task to delete.
     */
    override suspend fun delete(task: Task) =
        withContext(Dispatchers.IO) { taskDAO.delete(task.toEntity()) }

    /**
     * Retrieves all tasks from the database.
     * @return A [Flow] emitting a list of all tasks.
     */
    override fun getAllTasks(): Flow<List<Task>> =
        taskDAO.getAllTasks().map { it.toDomainList() }

    /**
     * Retrieves a task by its id.
     * @param id The id of the task.
     * @return A [Flow] emitting the task.
     */
    override fun getTaskById(id: Int): Flow<Task?> =
        taskDAO.getTaskById(id).map { it?.toDomain() }

    /**
     * Retrieves tasks whose names contain the specified text.
     * @param name The text to search for.
     * @return A [Flow] emitting a list of matching tasks.
     */
    override fun getTasksByName(name: String): Flow<List<Task>> =
        taskDAO.getTasksByName(name).map { it.toDomainList() }
}