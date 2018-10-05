package com.jfldev_francisco.wounderandroidtest.view.map

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import com.jfldev_francisco.wounderandroidtest.R
import com.jfldev_francisco.wounderandroidtest.model.Placemark
import com.jfldev_francisco.wounderandroidtest.viewModel.PlacemarkersViewModel
import java.util.ArrayList
import com.google.android.gms.maps.model.Marker

class MapFragment: Fragment(), OnMapReadyCallback {
    private var mapView: MapView? = null
    private var placemarksList: ArrayList<Placemark> = ArrayList()
    private var googleMap: GoogleMap? = null
    private lateinit var viewModel: PlacemarkersViewModel
    private var previousMarker: Marker? = null
    private lateinit var markers: ArrayList<Marker>

    override fun onMapReady(map: GoogleMap?) {
        this.googleMap = map
        setMarkers()

        googleMap?.setOnMarkerClickListener { marker ->
            var visibility = false

            if (previousMarker != null) {
                previousMarker?.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                previousMarker = null
                visibility = true
            } else {
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                marker.showInfoWindow()
                previousMarker = marker
            }
            setMarkersVisibility(visibility, marker)
            googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 12.72F))
            true
        }
    }

    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        placemarksList = ArrayList<Placemark>()

        viewModel = ViewModelProviders.of(this).get(PlacemarkersViewModel::class.java)
        viewModel.getAllPlacemarkers().observe(this, Observer {
            if (it != null) {
                (placemarksList).addAll(it)
            }
        })
        return inflater.inflate(R.layout.map_fragment_layout, container, false)
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)
        mapView = activity?.findViewById<MapView>(R.id.map)
        mapView?.onCreate(savedInstanceState)
        mapView?.onResume()
        mapView?.getMapAsync(this)
    }

    /**
     * Put the markers on the map*/
    fun setMarkers() {
        markers = ArrayList()
        for (p in placemarksList) {
            googleMap?.addMarker(MarkerOptions()
                    .position(
                            LatLng(p.coordinates[1],
                                    p.coordinates[0])).title(p.name)
                    .snippet(p.address))?.let { markers.add(it) }
        }
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(53.5438965, 9.9829945), 12.72F))
    }

    /**
     * Show and hide non selected markers */
    fun setMarkersVisibility(visibility: Boolean, currentMarker: Marker){
        for (marker: Marker in markers){
            if (marker == currentMarker){
                continue
            }
            marker.isVisible = visibility
        }
    }
}