package com.example.employeeandroomapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.employeeandroomapp.models.Employee
import com.example.employeeandroomapp.models.Rooms

@Database(
    entities = [Employee::class, Rooms::class],
    version = 1,
    exportSchema = false
)

abstract class DirectoryDatabase: RoomDatabase()
{
    abstract fun directoryDao(): DirectoryDao
}