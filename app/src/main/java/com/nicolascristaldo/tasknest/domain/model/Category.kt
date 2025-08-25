package com.nicolascristaldo.tasknest.domain.model

/**
 * Represents the category of a task.
 * @property color the associated color of the category.
 */
enum class Category(val color: Int) {
    PERSONAL(color = 0x90EE90.toInt()),
    WORK(color = 0xFF87CEFA.toInt()),
    SHOPPING(color = 0xFF69B4.toInt()),
    URGENT(color = 0xFF0000.toInt()),
    OTHER(color = 0xFFD3D3D3.toInt())
}
