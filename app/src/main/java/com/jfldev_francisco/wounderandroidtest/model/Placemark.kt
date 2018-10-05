package com.jfldev_francisco.wounderandroidtest.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.jfldev_francisco.wounderandroidtest.helpers.CustomTypeConverter

@Entity(tableName = "placemark_table")
data class Placemark(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val address: String,
        val coordinates: List<Double>,
        val engineType: String,
        val exterior: String,
        val fuel: Int,
        val interior: String,
        val name: String,
        val vin: String
)