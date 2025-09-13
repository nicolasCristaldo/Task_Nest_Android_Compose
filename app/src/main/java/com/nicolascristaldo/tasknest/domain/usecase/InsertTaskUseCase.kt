package com.nicolascristaldo.tasknest.domain.usecase

import com.nicolascristaldo.tasknest.data.worker.NotificationScheduler
import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.domain.repository.TaskRepository
import javax.inject.Inject

/**
 * Use case for inserting a task into the database.
 * @param repository The task repository.
 * @param notificationScheduler The notification scheduler.
 */
class InsertTaskUseCase @Inject constructor(
    private val repository: TaskRepository,
    private val notificationScheduler: NotificationScheduler
) {
    /**
     * Inserts a task into the database.
     * @param task The task to insert.
     */
    suspend operator fun invoke(task: Task) {
        repository.insert(task)
        if (task.isNotificationEnabled && task.date != null) {
            notificationScheduler.scheduleTaskNotification(task)
        }
    }
}