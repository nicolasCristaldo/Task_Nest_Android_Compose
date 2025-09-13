package com.nicolascristaldo.tasknest.data.worker

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nicolascristaldo.tasknest.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Worker for scheduling task notifications.
 * @param context The application context.
 * @param workerParams The worker parameters.
 */
class TaskNotificationWorker @Inject constructor(
    @ApplicationContext context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val taskId = inputData.getInt("TASK_ID", 0)
            val taskName = inputData.getString("TASK_NAME") ?: "Task"
            val taskDescription = inputData.getString("TASK_DESCRIPTION") ?: ""
            val title = applicationContext.getString(R.string.task_reminder, taskName)

            val notification = NotificationCompat.Builder(applicationContext, "TASK_CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(taskDescription)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build()

            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(taskId, notification)
            Result.success()
        }
        catch (e: Exception) {
            Result.failure()
        }
    }
}