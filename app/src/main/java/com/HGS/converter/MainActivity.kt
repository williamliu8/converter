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
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })

        // When you press a button in the bottom navigation view, update the view pager.
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            viewPager.currentItem = contentPagerAdapter.getPosition(menuItem.itemId)
            true
        }
    }
}
