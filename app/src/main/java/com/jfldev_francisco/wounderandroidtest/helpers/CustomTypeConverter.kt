package com.jfldev_francisco.wounderandroidtest.helpers

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import java.io.Serializable
import java.util.*


class CustomTypeConverter: Serializable {
    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Double> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Double>>() {

        }.type

        return gson.fromJson<List<Double>>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Double>): String {
        return gson.toJson(someObjects)
    }
}