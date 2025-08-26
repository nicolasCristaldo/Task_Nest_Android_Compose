package com.nicolascristaldo.tasknest.di.module

import android.content.Context
import androidx.room.Room
import com.nicolascristaldo.tasknest.data.room.db.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    /**
     * Provides a singleton instance of [TaskDatabase].
     * @param context The application context.
     * @return The [TaskDatabase] instance.
     */
    @Provides
    @Singleton
    fun provideTaskDatabase(@ApplicationContext context: Context): TaskDatabase =
        Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "task_database"
        ).build()

    /**
     * Provides a singleton instance of [TaskDAO].
     * @param taskDatabase The [TaskDatabase] instance.
     * @return The [TaskDAO] instance.
     */
    @Provides
    @Singleton
    fun provideTaskDAO(taskDatabase: TaskDatabase) = taskDatabase.taskDAO()
}