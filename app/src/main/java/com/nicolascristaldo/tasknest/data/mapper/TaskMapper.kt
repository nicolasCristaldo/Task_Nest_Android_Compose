package com.nicolascristaldo.tasknest.data.mapper

import com.nicolascristaldo.tasknest.data.room.entity.TaskEntity
import com.nicolascristaldo.tasknest.domain.model.Task

/**
 * Converts a [Task] to a [TaskEntity].
 * @return The [TaskEntity] representation of the [Task].
 */
fun Task.toEntity() = TaskEntity(
    id = id,
    name = name,
    description = description,
    date = date,
    isNotificationEnabled = isNotificationEnabled,
    category = category,
    status = status
)

/**
 * Converts a list of [Task] to a list of [TaskEntity].
 * @return The list of [TaskEntity] representations of the [Task] list.
 */
fun List<Task>.toEntityList() = map { it.toEntity() }

/**
 * Converts a [TaskEntity] to a [Task].
 * @return The [Task] representation of the [TaskEntity].
 */
fun TaskEntity.toDomain() = Task(
    id = id,
    name = name,
    description = description,
    date = date,
    isNotificationEnabled = isNotificationEnabled,
    category = category,
    status = status
)

/**
 * Converts a list of [TaskEntity] to a list of [Task].
 * @return The list of [Task] representations of the [TaskEntity] list.
 */
fun List<TaskEntity>.toDomainList() = map { it.toDomain() }