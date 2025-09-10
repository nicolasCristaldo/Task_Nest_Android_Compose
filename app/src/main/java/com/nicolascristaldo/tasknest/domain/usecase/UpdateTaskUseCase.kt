package com.nicolascristaldo.tasknest.domain.usecase

import com.nicolascristaldo.tasknest.data.worker.NotificationScheduler
import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.domain.repository.TaskRepository
import javax.inject.Inject

/**
 * Use case for updating a task in the database.
 * @param repository The task repository.
 * @param notificationScheduler The notification scheduler.
 */
class UpdateTaskUseCase @Inject constructor(
    private val repository: TaskRepository,
    private val notificationScheduler: NotificationScheduler
) {
    /**
     * Updates a task in the database.
     * @param task The task to update.
     */
    suspend operator fun invoke(task: Task) {
        repository.update(task)
        notificationScheduler.cancelTaskNotification(task.id)
        notificationScheduler.scheduleTaskNotification(task)
    }
}