package com.nicolascristaldo.tasknest.domain.model

/**
 * Represents the category of a task.
 * @property color the associated color of the category.
 */
enum class Category(val color: Int) {
    PERSONAL(color = 0xFF5EB420.toInt()),
    WORK(color = 0xFF34A0E8.toInt()),
    SHOPPING(color = 0xFFE38E11.toInt()),
    URGENT(color = 0xFFF33F3F.toInt()),
    OTHER(color = 0xFFCC24E7.toInt())
}
