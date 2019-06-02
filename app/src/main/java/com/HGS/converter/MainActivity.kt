package com.HGS.converter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val temperatureFragment = TemperatureFragment()
    val volumeFragment = VolumeFragment()
    val weightFragment = WeightFragment()
    val areaFragment = AreaFragment()
    val lengthFragment = LengthFragment()

    val fragments = mapOf(R.id.temperature to temperatureFragment,
                         R.id.volume to volumeFragment,
                         R.id.weight to weightFragment,
                         R.id.area to areaFragment,
                         R.id.length to lengthFragment
                        )

    var activeFragment: Fragment = temperatureFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // this is the first time rendering the activity, so show
            // the temperature fragment
            supportFragmentManager.beginTransaction()
                .add(R.id.content, temperatureFragment)
                .commit()
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            val fragmentToDisplayOnTheScreen = fragments[menuItem.itemId]!!
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragmentToDisplayOnTheScreen)
                .commit()
            activeFragment = fragmentToDisplayOnTheScreen
            true
        }
    }
}
