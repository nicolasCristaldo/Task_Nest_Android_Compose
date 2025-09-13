package com.nicolascristaldo.tasknest.domain.model

/**
 * Represents a task.
 * @property id The unique identifier of the task.
 * @property name The name of the task.
 * @property description The description of the task.
 * @property date The date of the task.
 * @property isNotificationEnabled Whether the task has a notification enabled.
 * @property category The category of the task.
 * @property status The status of the task.
 */
data class Task(
    val id: Int = 0,
    val name: String,
    val description: String? = null,
    val date: Long? = null,
    val isNotificationEnabled: Boolean = false,
    val category: Category = Category.OTHER,
    val status: Status = Status.PENDING
)
