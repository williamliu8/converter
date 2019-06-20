package com.HGS.converter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewPager
import android.util.DisplayMetrics
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

//for precisely locate the fab
//internal val TAG = popupActivity::class.java.simpleName


class MainActivity : AppCompatActivity() {

    private val contentPagerAdapter = ContentPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tell the view pager what fragments it should cycle through.
        viewPager.adapter = contentPagerAdapter
        // When someone swipes between fragments, update the bottom navigation view.
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                val menuItem = bottomNavigationView.menu.getItem(position)
                menuItem.isChecked = true
                setTitle(
                    when (menuItem.itemId) {
                        R.id.temperature -> R.string.temperature
                        R.id.volume -> R.string.volume
                        R.id.weight -> R.string.weight
                        R.id.area -> R.string.area
                        R.id.length -> R.string.length
                        else -> R.string.temperature
                    }
                )
            }
        })

        setTitle(R.string.temperature)

        // When you press a button in the bottom navigation view, update the view pager.
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->

            viewPager.currentItem = contentPagerAdapter.getPosition(menuItem.itemId)

            true
        }

        val fab = findViewById(R.id.calcfab) as FloatingActionButton
        val fabView: View = findViewById(R.id.calcfab)


        var x: Float = 0f
        var y: Float = 0f
        var startX: Float = 0f
        var startRawX: Float = 0f
        var startY: Float = 0f
        var startRawY: Float = 0f
        var distanceX:Float = 0f
        var distanceY:Float = 0f
        var drag:Boolean = false

        fabView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startX = v.getX() - event.getRawX()
                        startRawX = event.getRawX()
                        startY = v.getY() - event.getRawY()
                        startRawY = event.getRawY()
                    }
                    MotionEvent.ACTION_MOVE -> {
                        x = event.getRawX() + startX
                        y = event.getRawY() + startY
                        v.x = x
                        v.y = y
                    }
                    MotionEvent.ACTION_UP -> {
                        distanceX = event.getRawX()-startRawX
                        distanceY = event.getRawY()-startRawY
                        if (Math.abs(distanceX)<10f&&Math.abs(distanceY)<10f){
                            drag=false
                        }
                        else{
                            drag=true
                        }
                    }
                }
                return v?.onTouchEvent(event) ?: true
            }
        })

        fab.setOnClickListener{view ->
            if(drag==false){
                val popactive = Intent(this, popupActivity::class.java)
                startActivity(popactive)
            }
         }


    }

}
