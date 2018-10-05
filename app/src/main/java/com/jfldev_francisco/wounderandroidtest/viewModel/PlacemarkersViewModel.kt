package com.jfldev_francisco.wounderandroidtest.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.support.annotation.NonNull
import android.util.Log
import com.jfldev_francisco.wounderandroidtest.model.Placemark
import com.jfldev_francisco.wounderandroidtest.repository.PlacemarkRepository


class PlacemarkersViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: PlacemarkRepository
    private var allPlacemarkers: LiveData<List<Placemark>>

    init {
        repository = PlacemarkRepository(application)
        Log.i("From View Model", "Gettin all place markers >>>")
        allPlacemarkers = repository.getAllPlacemarkers()
    }

    fun insert(placemark: Placemark) {
        repository.insert(placemark)
    }

    fun update(placemark: Placemark) {
        repository.update(placemark)
    }

    fun delete(placemark: Placemark) {
        repository.delete(placemark)
    }

    fun deleteAllPlacemarkers() {
        repository.deleteAllPlacemarks()
    }

    fun getAllPlacemarkers(): LiveData<List<Placemark>> {
        return allPlacemarkers
    }

//    fun downloadData(){
//        repository.downloadData()
//    }
}