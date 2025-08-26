package com.nicolascristaldo.tasknest.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nicolascristaldo.tasknest.data.room.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing tasks in the database.
 */
@Dao
interface TaskDAO {
    /**
     * Inserts a task into the database.
     * @param task The task to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity)

    /**
     * Updates a task in the database.
     * @param task The task to update.
     */
    @Update
    suspend fun update(task: TaskEntity)

    /**
     * Deletes a task from the database.
     * @param task The task to delete.
     */
    @Delete
    suspend fun delete(task: TaskEntity)

    /**
     * Retrieves all tasks from the database.
     * @return A [Flow] emitting a list of all tasks.
     */
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    /**
     * Retrieves a task by its id.
     * @param id The id of the task.
     * @return A [Flow] emitting the task.
     */
    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTaskById(id: Int): Flow<TaskEntity>

    /**
     * Retrieves tasks whose names contain the specified text.
     * @param name The text to search for.
     * @return A [Flow] emitting a list of matching tasks.
     */
    @Query("SELECT * FROM tasks WHERE LOWER(name) LIKE '%' || LOWER(:name) || '%' ")
    fun getTasksByName(name: String): Flow<List<TaskEntity>>

    /**
     * Retrieves tasks by their category.
     * @param category The category of the task.
     * @return A [Flow] emitting a list of the tasks with the specified category.
     */
    @Query("SELECT * FROM tasks WHERE category = :category")
    fun getTasksByCategory(category: String): Flow<List<TaskEntity>>

    /**
     * Retrieves tasks by their status.
     * @param status The status of the task.
     * @return A [Flow] emitting a list of the tasks with the specified status.
     */
    @Query("SELECT * FROM tasks WHERE status = :status")
    fun getTasksByStatus(status: String): Flow<List<TaskEntity>>
}