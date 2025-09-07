package com.nicolascristaldo.tasknest.domain.model

import com.nicolascristaldo.tasknest.R

/**
 * Represents the status of a task.
 * @property icon The resource ID of the corresponding icon.
 */
enum class Status(val icon: Int) {
    IN_PROGRESS(R.drawable.ic_in_progress),
    COMPLETED(R.drawable.ic_completed),
    PENDING(R.drawable.ic_pending)
}