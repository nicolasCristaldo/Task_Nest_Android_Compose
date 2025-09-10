package com.nicolascristaldo.tasknest.domain.model

import androidx.annotation.StringRes
import com.nicolascristaldo.tasknest.R

/**
 * Represents the category of a task.
 * @property color the associated color of the category.
 * @property stringResId the string resource ID of the category name.
 */
enum class Category(val color: Int, @StringRes val stringResId: Int) {
    PERSONAL(color = 0xFF5EB420.toInt(), stringResId = R.string.category_personal),
    WORK(color = 0xFF34A0E8.toInt(), stringResId = R.string.category_work),
    SHOPPING(color = 0xFFE38E11.toInt(), stringResId = R.string.category_shopping),
    URGENT(color = 0xFFF33F3F.toInt(), stringResId = R.string.category_urgent),
    OTHER(color = 0xFFCC24E7.toInt(), stringResId = R.string.category_other)
}
