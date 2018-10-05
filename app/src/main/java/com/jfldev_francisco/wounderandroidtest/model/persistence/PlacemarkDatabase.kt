package com.jfldev_francisco.wounderandroidtest.model.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase

import android.content.Context
import com.jfldev_francisco.wounderandroidtest.model.Placemark
import com.jfldev_francisco.wounderandroidtest.model.PlacemarkDAO
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.TypeConverters
import android.os.AsyncTask
import android.support.annotation.NonNull
import android.util.Log
import com.jfldev_francisco.wounderandroidtest.helpers.CustomTypeConverter
import com.jfldev_francisco.wounderandroidtest.model.Locations
import com.jfldev_francisco.wounderandroidtest.service.WounderAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Database class to persist list of Placemarks
 * */
@Database(entities = arrayOf(Placemark::class), version = 2)
@TypeConverters(CustomTypeConverter::class)
abstract class PlacemarkDatabase: RoomDatabase() {
    abstract fun placemarkerDAO(): PlacemarkDAO

    companion object {
        private var INSTANCE: PlacemarkDatabase? = null
        
        fun getInstance(context: Context): PlacemarkDatabase? {
            if (INSTANCE == null) {
                synchronized(PlacemarkDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            PlacemarkDatabase::class.java, "wounder_test")
                            .addCallback(roomCb)
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        private val roomCb = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { PopulateDbAsyncTask(it).execute() }
            }
        }
    }

    class PopulateDbAsyncTask() : AsyncTask<Void, Void, Void>() {
        private lateinit var placemarkerDAO: PlacemarkDAO

        constructor(db: PlacemarkDatabase) : this () {
            placemarkerDAO = db.placemarkerDAO()
        }
        override fun doInBackground(vararg params: Void?): Void? {

            //placemarkerDAO.insert()
            return null
        }

    }

    fun destroyInstance() {
        INSTANCE == null
    }
}