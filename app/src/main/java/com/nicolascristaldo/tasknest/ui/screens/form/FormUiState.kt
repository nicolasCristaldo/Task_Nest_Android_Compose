package com.nicolascristaldo.tasknest.ui.screens.form

import com.nicolascristaldo.tasknest.domain.model.Category
import com.nicolascristaldo.tasknest.domain.model.Status
import com.nicolascristaldo.tasknest.domain.model.Task

/**
 * Represents the UI state for the TaskFormScreen.
 * @property taskDetails The details of the task being edited or created.
 * @property error The error message to be displayed, if any.
 */
data class FormUiState(
    val taskDetails: TaskDetails = TaskDetails(),
    val error: String? = null
) {
    /**
     * Checks if the name of the task is valid.
     * @return `true` if the name is not blank and has a length less than or equal to 30, otherwise `false`.
     */
    fun isNameValid(): Boolean = taskDetails.name.isNotBlank() && taskDetails.name.length <= 30

    /**
     * Checks if the description of the task is valid.
     * @return `true` if the description has a length less than or equal to 200, otherwise `false`.
     */
    fun isDescriptionValid(): Boolean = taskDetails.description.length <= 200

    /**
     * Checks if the form is in a valid state.
     * @return `true` if both the name and description are valid, otherwise `false`.
     */
    fun isEntryValid(): Boolean = isNameValid() && isDescriptionValid()
}

/**
 * Represents the details of a task.
 * @property id The unique identifier of the task.
 * @property name The name of the task.
 * @property description The description of the task.
 * @property date The date of the task.
 * @property isNotificationEnabled Whether the task has a notification enabled.
 * @property category The category of the task.
 * @property status The status of the task.
 */
data class TaskDetails(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val date: Long? = null,
    val isNotificationEnabled: Boolean = false,
    val category: Category = Category.OTHER,
    val status: Status = Status.PENDING
)

/**
 * Converts a [Task] object to a [TaskDetails] object.
 */
fun Task.toTaskDetails(): TaskDetails = TaskDetails(
    id = id,
    name = name,
    description = description ?: "",
    date = date,
    isNotificationEnabled = isNotificationEnabled,
    category = category,
    status = status
)

/**
 * Converts a [TaskDetails] object to a [Task] object.
 */
fun TaskDetails.toTask(): Task = Task(
    id = id,
    name = name,
    description = description.ifBlank { null },
    date = date,
    isNotificationEnabled = isNotificationEnabled,
    category = category,
    status = status
)