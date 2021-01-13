package com.example.employeeandroomapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.employeeandroomapp.InstantAppExecutors
import com.example.employeeandroomapp.api.ApiResponse
import com.example.employeeandroomapp.api.DirectoryApiService
import com.example.employeeandroomapp.database.DirectoryDao
import com.example.employeeandroomapp.database.DirectoryDatabase
import com.example.employeeandroomapp.models.Employee
import com.example.employeeandroomapp.repository.util.Resource
import com.example.employeeandroomapp.util.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import retrofit2.Response

class DirectoryRepositoryTest {
    private lateinit var directoryRepository: DirectoryRepository
    private val dao = Mockito.mock(DirectoryDao::class.java)
    private val service = Mockito.mock(DirectoryApiService::class.java)

    @Rule
    @JvmField
    val instantRuleExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val db = Mockito.mock(DirectoryDatabase::class.java)
        Mockito.`when`(db.directoryDao()).thenReturn(dao)
        Mockito.`when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        directoryRepository = DirectoryRepository(InstantAppExecutors(), db, dao, service)

    }

    @Test
    fun searchEmployeeTest() {
        val emp1 = Employee(
            "some url",
            "234234",
            "ABC",
            1,
            243.234,
            "Blue",
            "abc@xyz.com",
            "developer",
            "12-01-2021",
            2332.234,
            "LMN"
        )
        val emp2 = Employee(
            "some other url",
            "234234",
            "PQR",
            2,
            243243.232344,
            "Red",
            "pqr@xyz.com",
            "developer",
            "13-01-2021",
            254332.234,
            "UVW"
        )

        val observer = mock<Observer<Resource<List<Employee>>>>()
        val dbSearchResult = MutableLiveData<List<Employee>>()
        val empMutableList = MutableLiveData<List<Employee>>()

        val emplSimpleList = arrayListOf(emp1, emp2)
        val apiResponse = emplSimpleList

        val callLiveData = MutableLiveData<ApiResponse<List<Employee>>>()

        Mockito.`when`(service.searchEmployees()).thenReturn(callLiveData)

        Mockito.`when`(dao.getAllEmployees()).thenReturn(dbSearchResult)

        directoryRepository.getEmployeeData().observeForever(observer)

        Mockito.verify(observer).onChanged(Resource.loading(null))
        Mockito.verifyNoMoreInteractions(service)
        Mockito.reset(observer)

        Mockito.doReturn(empMutableList).`when`(dao).getAllEmployees()

        dbSearchResult.postValue(null)
        Mockito.verify(dao, Mockito.atLeastOnce()).getAllEmployees()

        Mockito.verify(service).searchEmployees()
        val updatedResult = MutableLiveData<List<Employee>>()

        Mockito.`when`(dao.getAllEmployees()).thenReturn(updatedResult)
        updatedResult.postValue(emplSimpleList)

        callLiveData.postValue(ApiResponse.create(Response.success(apiResponse)))
        Mockito.verify(dao).insertEmployees(emplSimpleList)
        empMutableList.postValue(emplSimpleList)
        Mockito.verify(observer).onChanged(Resource.success(emplSimpleList))
        Mockito.verifyNoMoreInteractions(service)
    }
}