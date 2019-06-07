package com.HGS.converter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val contentPagerAdapter = ContentPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tell the view pager what fragments it should cycle through.
        viewPager.adapter = contentPagerAdapter
        // When someone swipes between fragments, update the bottom navigation view.
        viewPager.addOnPageChangeListener(object: ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                val menuItem = bottomNavigationView.menu.getItem(position)
                menuItem.isChecked = true
                setTitle(when (menuItem.itemId) {
                    R.id.temperature -> R.string.temperature
                    R.id.volume -> R.string.volume
                    R.id.weight -> R.string.weight
                    R.id.area -> R.string.area
                    R.id.length -> R.string.length
                    else -> R.string.temperature
                })
            }
        })

        setTitle(R.string.temperature)

        // When you press a button in the bottom navigation view, update the view pager.
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->

            viewPager.currentItem = contentPagerAdapter.getPosition(menuItem.itemId)

            true
        }
    }
}
