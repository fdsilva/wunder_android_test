package com.jfldev_francisco.wounderandroidtest.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "locations_table")
data class Locations(@PrimaryKey(autoGenerate = true)
                     val id: Int,
                     val placemarks: List<Placemark>)