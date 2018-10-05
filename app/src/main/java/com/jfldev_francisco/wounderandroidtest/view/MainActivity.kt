package com.jfldev_francisco.wounderandroidtest.view

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import com.jfldev_francisco.wounderandroidtest.R
import com.jfldev_francisco.wounderandroidtest.view.adapters.ViewPagerAdapter
import com.jfldev_francisco.wounderandroidtest.view.carsList.CarListFragment
import com.jfldev_francisco.wounderandroidtest.view.map.MapFragment

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewPager = findViewById(R.id.viewPager)
        setupViewPager(viewPager)

        tabLayout = findViewById(R.id.tablayout)
        tabLayout.setupWithViewPager(viewPager)
        setTabIcons()
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(CarListFragment.newInstance(),
                resources.getString(R.string.tab_vehicle_available))
        adapter.addFragment(MapFragment.newInstance(), resources.getString(R.string.tab_map))
        viewPager.adapter = adapter
    }

    @SuppressLint("ResourceType")
    private fun setTabIcons() {
        val tabIcons = resources.obtainTypedArray(R.array.tabIcons)
        tabLayout.getTabAt(0)!!.setIcon(tabIcons.getResourceId(0, -1))
        tabLayout.getTabAt(1)!!.setIcon(tabIcons.getResourceId(1, -1))
    }
}
