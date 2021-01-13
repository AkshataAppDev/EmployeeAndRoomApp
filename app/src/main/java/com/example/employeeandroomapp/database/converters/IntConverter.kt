package com.example.employeeandroomapp.database.converters

import androidx.room.TypeConverter

object IntConverter {
    @TypeConverter
    fun toString(number: Int): String? {
        return number.toString()
    }

    @TypeConverter
    fun toInt(str: String): Int? {
        return str.toInt()
    }
}