package com.example.employeeandroomapp.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.employeeandroomapp.util.LiveDataTestUtil.getValue
import com.example.employeeandroomapp.utils.Constants
import com.example.employeeandroomapp.utils.LiveDataCallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class DirectoryApiServiceTest {


    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: DirectoryApiService
    private var mockWebServer = MockWebServer()

    @Before
    fun createService() {
        service = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(DirectoryApiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getEmployeesTest() {
        enqueueResponse("employee.json")
        val response = getValue(service.searchEmployees()) as ApiSuccessResponse

        assertThat(response, notNullValue())
    }

    @Test
    fun getRoomTest() {
        enqueueResponse("room.json")
        val response = getValue(service.searchEmployees()) as ApiSuccessResponse

        assertThat(response, notNullValue())
    }


    private fun enqueueResponse(@Suppress("SameParameterValue") fileName: String) {
        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val inputStream = javaClass.classLoader
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )

        mockWebServer.start()
    }


}