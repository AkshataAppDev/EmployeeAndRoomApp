package com.example.employeeandroomapp.api

import androidx.lifecycle.LiveData
import com.example.employeeandroomapp.models.Employee
import com.example.employeeandroomapp.models.Rooms
import retrofit2.http.GET

interface DirectoryApiService
{

//   https://5f7c2c8400bd74001690a583.mockapi.io/api/v1/people

    @GET("people")
    fun searchEmployees(): LiveData<ApiResponse<List<Employee>>>


//    https://5f7c2c8400bd74001690a583.mockapi.io/api/v1/rooms
    @GET("rooms")
    fun searchRooms(): LiveData<ApiResponse<List<Rooms>>>

}