package com.HGS.converter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ContentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments = listOf(TemperatureFragment(),
        VolumeFragment(),
        WeightFragment(),
        AreaFragment(),
        LengthFragment())

    override fun getItem(position: Int): Fragment? {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun getPosition(id: Int): Int {
        return when (id) {
            R.id.temperature -> 0
            R.id.volume -> 1
            R.id.weight -> 2
            R.id.area -> 3
            R.id.length -> 4
            else -> 0 // this should never happen
        }
    }
}