package com.jfldev_francisco.wounderandroidtest.model

import android.arch.persistence.room.*
import android.arch.lifecycle.LiveData

@Dao
interface PlacemarkDAO {

    @Insert
    fun insert(placemark: Placemark?)

    @Update
    fun update(placemark: Placemark?)

    @Delete
    fun delete(placemark: Placemark?)

    @Query("DELETE FROM placemark_table")
    fun deleteAllPlacemarks()

    @Query("SELECT * FROM placemark_table ORDER BY id")
    fun getAllPlacemarkers(): LiveData<List<Placemark>>
}