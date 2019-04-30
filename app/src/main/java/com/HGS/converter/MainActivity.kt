package com.HGS.converter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    val fragments = mapOf(R.id.temperature to TemperatureFragment(),
                         R.id.volume to VolumeFragment(),
                         R.id.weight to WeightFragment(),
                         R.id.currency to CurrencyFragment()
                        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
