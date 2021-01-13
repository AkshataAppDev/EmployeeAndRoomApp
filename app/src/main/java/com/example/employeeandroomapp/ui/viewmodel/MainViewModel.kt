package com.example.employeeandroomapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.employeeandroomapp.models.Employee
import com.example.employeeandroomapp.models.Rooms
import com.example.employeeandroomapp.repository.DirectoryRepository
import com.example.employeeandroomapp.repository.util.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(private val directoryRepository: DirectoryRepository) : ViewModel()
{
    private val _empId = MutableLiveData<Int>()

    val results : LiveData<Resource<List<Employee>>> = directoryRepository.getEmployeeData()

    val roomResults : LiveData<Resource<List<Rooms>>> = directoryRepository.getRoomsData()

    val employee : LiveData<Employee> = Transformations.switchMap(_empId){
        empId->
        directoryRepository.loadEmployeeById(empId)
    }

    fun setId(empId: Int)
    {
        _empId.value=empId
    }


}
