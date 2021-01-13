package com.example.employeeandroomapp.models

import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName


@Entity(
    indices = [Index("id")],
    primaryKeys = ["id"]
)
data class Rooms(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("created_at")
    val creationDate: String,

    @field:SerializedName("name")
    val roomName: String,

    @field:SerializedName("max_occupancy")
    val roomCapacity: Int,

    @field:SerializedName("is_occupied")
    val isOccupied: Boolean
)