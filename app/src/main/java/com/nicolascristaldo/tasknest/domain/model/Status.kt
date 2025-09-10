package com.nicolascristaldo.tasknest.domain.model

import androidx.annotation.StringRes
import com.nicolascristaldo.tasknest.R

/**
 * Represents the status of a task.
 * @property icon The resource ID of the corresponding icon.
 * @property stringResId The string resource ID of the status name.
 */
enum class Status(val icon: Int, @StringRes val stringResId: Int) {
    IN_PROGRESS(R.drawable.ic_in_progress, R.string.status_in_progress),
    COMPLETED(R.drawable.ic_completed, R.string.status_completed),
    PENDING(R.drawable.ic_pending, R.string.status_pending)
}