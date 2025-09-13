package com.nicolascristaldo.tasknest.data.room.converters

import androidx.room.TypeConverter
import com.nicolascristaldo.tasknest.domain.model.Category
import com.nicolascristaldo.tasknest.domain.model.Status

/**
 * Converts a [Category] to a [String] and vice versa for the database.
 */
class Converters {
    /**
     * Converts a [Category] to a [String].
     * @param category The category to convert.
     * @return The [String] representation of the category.
     */
    @TypeConverter
    fun fromCategory(category: Category): String = category.name

    /**
     * Converts a [String] to a [Category].
     * @param name The [String] representation of the category.
     * @return The [Category] represented by the [String].
     */
    @TypeConverter
    fun toCategory(name: String): Category = Category.valueOf(name)

    /**
     * Converts a [Status] to a [String].
     * @param status The status to convert.
     * @return The [String] representation of the status.
     */
    @TypeConverter
    fun fromStatus(status: Status): String = status.name

    /**
     * Converts a [String] to a [Status].
     * @param name The [String] representation of the status.
     * @return The [Status] represented by the [String].
     */
    @TypeConverter
    fun toStatus(name: String): Status = Status.valueOf(name)
}