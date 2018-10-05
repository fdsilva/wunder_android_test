package com.jfldev_francisco.wounderandroidtest.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jfldev_francisco.wounderandroidtest.R
import com.jfldev_francisco.wounderandroidtest.model.Placemark
import kotlinx.android.synthetic.main.placemark_iten_list.view.*

class PlacemarkAdapter(): RecyclerView.Adapter<PlacemarkAdapter.PlacemarkHolder>() {
    var placemarks: List<Placemark>

    init {
        placemarks = ArrayList<Placemark>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacemarkHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.placemark_iten_list, parent, false)
        return PlacemarkHolder(view)
    }

    override fun getItemCount(): Int {
        return placemarks.size
    }

    override fun onBindViewHolder(holder: PlacemarkHolder, position: Int) {
        val placemark = placemarks[position]
        holder.tvNome.text = placemark.name
        holder.tvAddress.text = placemark.address
    }

    fun addItems(placesList: List<Placemark>) {
        this.placemarks = placesList
        notifyDataSetChanged()
    }

    class PlacemarkHolder(itenView: View): RecyclerView.ViewHolder(itenView) {
        var tvNome = itenView.tvName
        var tvAddress = itenView.tvAddress
    }
}
