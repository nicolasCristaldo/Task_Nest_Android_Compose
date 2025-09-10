package com.nicolascristaldo.tasknest.di.module

import android.content.Context
import androidx.work.WorkManager
import com.nicolascristaldo.tasknest.data.worker.NotificationScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module for providing dependencies related to WorkManager.
 */
@Module
@InstallIn(SingletonComponent::class)
object WorkManagerModule {
    /**
     * Provides a singleton instance of [WorkManager].
     * @param context The application context.
     * @return The [WorkManager] instance.
     */
    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context) = WorkManager.getInstance(context)

    /**
     * Provides a singleton instance of [NotificationScheduler].
     * @param workManager The [WorkManager] instance.
     * @return The [NotificationScheduler] instance.
     */
    @Provides
    @Singleton
    fun provideNotificationScheduler(workManager: WorkManager) = NotificationScheduler(workManager)
}