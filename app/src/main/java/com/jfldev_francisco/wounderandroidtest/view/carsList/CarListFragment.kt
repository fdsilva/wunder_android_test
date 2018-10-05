package com.jfldev_francisco.wounderandroidtest.view.carsList

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jfldev_francisco.wounderandroidtest.R
import com.jfldev_francisco.wounderandroidtest.view.adapters.PlacemarkAdapter
import com.jfldev_francisco.wounderandroidtest.viewModel.PlacemarkersViewModel

class CarListFragment: Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var placemarkAdapter: PlacemarkAdapter
    lateinit private var viewModel: PlacemarkersViewModel

    companion object {
        fun newInstance(): CarListFragment {
            return CarListFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.car_list_fragment_layout, container,false)

        recyclerView = view.findViewById(R.id.recycleView)

        recyclerView.setLayoutManager(LinearLayoutManager(activity))
        recyclerView.setHasFixedSize(true)

        placemarkAdapter = PlacemarkAdapter()
        recyclerView.adapter = placemarkAdapter

        viewModel = ViewModelProviders.of(this).get(PlacemarkersViewModel::class.java)

        viewModel.getAllPlacemarkers().observe(this, Observer {
            if (it != null) {
                Log.d(">>>>","SHOULD WORK 1 <<<<")
                placemarkAdapter.addItems(it)

                Log.d(">>>>","SHOULD WORK <<<< ${it.size}")
            }
        })
        return view
    }
}