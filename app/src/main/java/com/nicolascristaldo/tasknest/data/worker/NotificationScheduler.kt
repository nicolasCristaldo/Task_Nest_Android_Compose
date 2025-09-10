package com.nicolascristaldo.tasknest.data.worker

import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.nicolascristaldo.tasknest.domain.model.Task
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Scheduler for task notifications.
 * @param workManager The [WorkManager] instance for scheduling notifications.
 */
class NotificationScheduler @Inject constructor(
    private val workManager: WorkManager
) {
    /**
     * Schedules a task notification.
     * @param task The [Task] to schedule a notification for.
     */
    fun scheduleTaskNotification(task: Task) {
        val currentTime = System.currentTimeMillis()
        val delay = task.date?.minus(currentTime) ?: return

        if (delay > 0 && task.isNotificationEnabled) {
            val data = Data.Builder()
                .putInt("TASK_ID", task.id)
                .putString("TASK_NAME", task.name)
                .putString("TASK_DESCRIPTION", task.description ?: "")
                .build()

            val workRequest = OneTimeWorkRequestBuilder<TaskNotificationWorker>()
                .setInputData(data)
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .addTag("TASK_NOTIFICATION_${task.id}")
                .build()

            workManager.enqueue(workRequest)
        }
    }

    /**
     * Cancels a task notification.
     * @param taskId The ID of the task to cancel the notification for.
     */
    fun cancelTaskNotification(taskId: Int) {
        workManager.cancelAllWorkByTag("TASK_NOTIFICATION_$taskId")
    }
}