package com.example.employeeandroomapp.models

import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
    indices = [Index("id")],
    primaryKeys = ["id"]
)
data class Employee(

    @field:SerializedName("avatar")
    val imgUrl: String,

    @field:SerializedName("phone")
    val phone : String,

    @field:SerializedName("firstName")
    val firstName: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("longitude")
    val longitude: Double,

    @field:SerializedName("favouriteColor")
    val favColor: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("jobTitle")
    val jobTitle: String,

    @field:SerializedName("createdAt")
    val creationDate: String,

    @field:SerializedName("latitude")
    val latitude: Double,

    @field:SerializedName("lastName")
    val lastName: String
)