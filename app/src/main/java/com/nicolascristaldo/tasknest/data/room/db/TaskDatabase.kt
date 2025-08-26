package com.nicolascristaldo.tasknest.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicolascristaldo.tasknest.data.room.converters.Converters
import com.nicolascristaldo.tasknest.data.room.dao.TaskDAO
import com.nicolascristaldo.tasknest.data.room.entity.TaskEntity

/**
 * Represents the database for tasks.
 */
@Database(entities = [TaskEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class TaskDatabase: RoomDatabase() {
    /**
     * Retrieves the DAO for tasks.
     * @return The [TaskDAO] for the task entity.
     */
    abstract fun taskDAO(): TaskDAO
}