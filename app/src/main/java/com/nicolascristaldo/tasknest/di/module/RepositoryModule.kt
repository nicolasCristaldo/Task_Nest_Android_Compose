package com.nicolascristaldo.tasknest.di.module

import com.nicolascristaldo.tasknest.data.repository.TaskRepositoryImpl
import com.nicolascristaldo.tasknest.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module for providing the TaskRepository implementation.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    /**
     * Binds the TaskRepositoryImpl to the TaskRepository interface.
     * @param taskRepositoryImpl The implementation of TaskRepository.
     * @return The bound TaskRepository interface.
     */
    @Binds
    @Singleton
    abstract fun bindTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository
}