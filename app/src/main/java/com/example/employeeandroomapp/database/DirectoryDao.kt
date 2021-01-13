package com.example.employeeandroomapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.employeeandroomapp.models.Employee
import com.example.employeeandroomapp.models.Rooms

@Dao
abstract class DirectoryDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEmployees(empList: List<Employee>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRooms(roomList: List<Rooms>)

    @Query("Select * from Employee order by Employee.firstName")
    abstract fun getAllEmployees(): LiveData<List<Employee>>

    @Query("Select * from Rooms order by Rooms.isOccupied ")
    abstract fun getAllRooms() : LiveData<List<Rooms>>

    @Query("Select * from Employee where id = :id")
    abstract fun getEmployeeById(id: Int) : LiveData<Employee>

}