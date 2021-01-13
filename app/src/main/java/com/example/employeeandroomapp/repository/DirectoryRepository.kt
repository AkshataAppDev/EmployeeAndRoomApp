package com.example.employeeandroomapp.repository

import androidx.lifecycle.LiveData
import com.example.employeeandroomapp.api.ApiResponse
import com.example.employeeandroomapp.api.ApiSuccessResponse
import com.example.employeeandroomapp.api.DirectoryApiService
import com.example.employeeandroomapp.database.DirectoryDao
import com.example.employeeandroomapp.database.DirectoryDatabase
import com.example.employeeandroomapp.models.Employee
import com.example.employeeandroomapp.models.Rooms
import com.example.employeeandroomapp.repository.util.AppExecutors
import com.example.employeeandroomapp.repository.util.NetworkBoundResource
import com.example.employeeandroomapp.repository.util.Resource
import com.example.employeeandroomapp.testing.OpenForTesting
import timber.log.Timber
import javax.inject.Inject

@OpenForTesting
class DirectoryRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val directoryDatabase: DirectoryDatabase,
    private val directoryDao: DirectoryDao,
    private val directoryApiService: DirectoryApiService
)
{
    companion object {
        private const val TAG = "DirectoryRepository"
    }

    fun loadEmployeeById(id: Int): LiveData<Employee>
    {
        return directoryDao.getEmployeeById(id)
    }

    fun getEmployeeData() : LiveData<Resource<List<Employee>>>
    {
        return object : NetworkBoundResource<List<Employee>, List<Employee>>(appExecutors)
        {
            override fun processResponse(response: ApiSuccessResponse<List<Employee>>): List<Employee> {
                Timber.d("Getting response")
                return response.body
            }

            override fun saveCallResult(item: List<Employee>) {
              Timber.d("Now saving to database")
                directoryDao.insertEmployees(item)
            }

            override fun shouldFetch(data: List<Employee>?): Boolean {
                return (data == null || data.isEmpty())
            }

            override fun loadFromDb(): LiveData<List<Employee>> {
                Timber.d("Loading from database")
                return directoryDao.getAllEmployees()
            }

            override fun createCall(): LiveData<ApiResponse<List<Employee>>> {
                Timber.d("Creating request")
                return directoryApiService.searchEmployees()
            }
        }.asLiveData()

    }

    fun getRoomsData(): LiveData<Resource<List<Rooms>>>
    {
        return object : NetworkBoundResource<List<Rooms>, List<Rooms>>(appExecutors)
        {
            override fun processResponse(response: ApiSuccessResponse<List<Rooms>>): List<Rooms> {
                Timber.d("Getting response")
                return response.body
            }

            override fun saveCallResult(item: List<Rooms>) {
                Timber.d("Now saving to database")
                directoryDao.insertRooms(item)
            }

            override fun shouldFetch(data: List<Rooms>?): Boolean {
                return (data == null || data.isEmpty())
            }

            override fun loadFromDb(): LiveData<List<Rooms>> {
                Timber.d("Loading from database")
                return directoryDao.getAllRooms()
            }

            override fun createCall(): LiveData<ApiResponse<List<Rooms>>> {
                Timber.d("Creating request")
                return directoryApiService.searchRooms()
            }
        }.asLiveData()

    }


}