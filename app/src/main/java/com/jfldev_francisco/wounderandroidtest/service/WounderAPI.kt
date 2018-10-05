package com.jfldev_francisco.wounderandroidtest.service

import com.jfldev_francisco.wounderandroidtest.helpers.BASE_URL
import com.jfldev_francisco.wounderandroidtest.helpers.ENDPOINT
import com.jfldev_francisco.wounderandroidtest.model.Locations
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WounderAPI {
    @GET(ENDPOINT)
    fun getLocations(): Call<Locations>

    companion object {
        fun create(): WounderAPI {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(WounderAPI::class.java)
        }
    }
}