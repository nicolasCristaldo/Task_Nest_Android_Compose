package com.nicolascristaldo.tasknest

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for the TaskNest application.
 */
@HiltAndroidApp
class TaskNestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    /**
     * Creates a notification channel for task reminders.
     */
    private fun createNotificationChannel() {
        val name = "Task Notifications"
        val descriptionText = "Notifications for task reminders"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("TASK_CHANNEL_ID", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}