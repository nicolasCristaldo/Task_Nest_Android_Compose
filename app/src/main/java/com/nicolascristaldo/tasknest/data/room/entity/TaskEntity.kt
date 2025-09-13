package com.nicolascristaldo.tasknest.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nicolascristaldo.tasknest.domain.model.Category
import com.nicolascristaldo.tasknest.domain.model.Status

/**
 * Represents a task entity in the database.
 * @property id The unique identifier of the task.
 * @property name The name of the task.
 * @property description The description of the task.
 * @property date The date of the task.
 * @property isNotificationEnabled Whether the task has a notification enabled.
 * @property category The category of the task.
 * @property status The status of the task.
 */
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "date") val date: Long? = null,
    @ColumnInfo(name = "notification_enabled") val isNotificationEnabled: Boolean = false,
    @ColumnInfo(name = "category") val category: Category,
    @ColumnInfo(name = "status") val status: Status
)