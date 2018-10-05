package com.jfldev_francisco.wounderandroidtest.repository

import android.app.Application
import com.jfldev_francisco.wounderandroidtest.model.PlacemarkDAO
import com.jfldev_francisco.wounderandroidtest.model.persistence.PlacemarkDatabase
import android.arch.lifecycle.LiveData
import com.jfldev_francisco.wounderandroidtest.model.Placemark
import android.os.AsyncTask
import android.util.Log
import com.jfldev_francisco.wounderandroidtest.model.Locations
import com.jfldev_francisco.wounderandroidtest.service.WounderAPI
import retrofit2.Call
import retrofit2.Response


class PlacemarkRepository(val application: Application) {

    private var placemarkDAO: PlacemarkDAO
    private var allPlacemarks: LiveData<List<Placemark>>

    var api = WounderAPI.create()
    var call = api.getLocations()

    init{
        var database: PlacemarkDatabase = PlacemarkDatabase.getInstance(application)!!
        placemarkDAO = database.placemarkerDAO()
        allPlacemarks = placemarkDAO.getAllPlacemarkers()
        allPlacemarks.observeForever {
            if(it?.size == 0) {
                downloadData()
            }
        }
    }

    fun insert(placemark: Placemark) {
        InsertPlacemarkAsyncTask(placemarkDAO).execute(placemark)
    }

    fun update(placemark: Placemark) {
        UpdatePlacemarkAsyncTask(placemarkDAO).execute(placemark)
    }

    fun delete(placemark: Placemark) {
        DeletePlacemarkAsyncTask(placemarkDAO).execute(placemark)
    }

    fun deleteAllPlacemarks() {
        DeleteAllPlacemarkAsyncTask(placemarkDAO).execute()
    }

    fun getAllPlacemarkers(): LiveData<List<Placemark>> {
        return allPlacemarks
    }

    fun downloadData() {

        call.enqueue(object : retrofit2.Callback<Locations> {
            override fun onResponse(call: Call<Locations>, response: Response<Locations>) {
                if (!response.isSuccessful()) {
                    Log.d(">>>> From Repository", "${response.code()} >>")
                } else {
                    for (placemak: Placemark in response.body()?.placemarks!!) {
                        insert(placemak)
                    }
                    Log.d(">>>> ", "${response.body()} >>")
                }
            }

            override fun onFailure(call: Call<Locations>, t: Throwable) {
                Log.d(">>>> ", "ZEBA >>")
            }
        })
    }

    class InsertPlacemarkAsyncTask() : AsyncTask<Placemark, Void, Void>() {
        lateinit private var placemarkDAO: PlacemarkDAO

        constructor(placemarkDAO: PlacemarkDAO) : this() {
            this.placemarkDAO = placemarkDAO
        }

        override fun doInBackground(vararg params: Placemark?): Void? {
            placemarkDAO.insert(params[0])
            return null
        }
    }

    /**
     * AsyncTask to handle dataBase Operations
     */
    class UpdatePlacemarkAsyncTask() : AsyncTask<Placemark, Void, Void>() {
        lateinit private var placemarkDAO: PlacemarkDAO

        constructor(placemarkDAO: PlacemarkDAO) : this() {
            this.placemarkDAO = placemarkDAO
        }

        override fun doInBackground(vararg params: Placemark?): Void? {
            placemarkDAO.update(params[0])
            return null
        }
    }

    class DeletePlacemarkAsyncTask() : AsyncTask<Placemark, Void, Void>() {
        lateinit private var placemarkDAO: PlacemarkDAO

        constructor(placemarkDAO: PlacemarkDAO) : this() {
            this.placemarkDAO = placemarkDAO
        }

        override fun doInBackground(vararg params: Placemark?): Void? {
            placemarkDAO.delete(params[0])
            return null
        }
    }

    class DeleteAllPlacemarkAsyncTask() : AsyncTask<Void, Void, Void>() {
        lateinit private var placemarkDAO: PlacemarkDAO

        constructor(placemarkDAO: PlacemarkDAO) : this() {
            this.placemarkDAO = placemarkDAO
        }

        override fun doInBackground(vararg voids: Void?): Void? {
            placemarkDAO.deleteAllPlacemarks()
            return null
        }
    }
}