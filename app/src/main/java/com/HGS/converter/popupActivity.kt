package com.HGS.converter

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_popup.*
import kotlinx.android.synthetic.main.fragment_length.*
import kotlinx.android.synthetic.main.fragment_volume.*
//for precisely locate the window
//internal val TAG = popupActivity::class.java.simpleName

class popupActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)

        //get the window View
        val windowView = findViewById<View>(R.id.window)

        //get the window parameter
        val params:WindowManager.LayoutParams = getWindow().getAttributes()

        //enable window background transparent
        getWindow().setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )


        //get the display information
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
            //display width
        var width:Int = dm.widthPixels
            //display height
        var height:Int = dm.heightPixels

        //define window height
        var windowHeight:Int = (dm.heightPixels*0.25).toInt()
        //set window width and height
        getWindow().setLayout(width,windowHeight)

        //define initial window align and position
        params.gravity = Gravity.LEFT or Gravity.TOP
        params.x= 0
        params.y= (height*0.37).toInt()
        getWindow().setAttributes(params)

        //window view onTouch detect
        //getRawX() is absolute X location from left-top = 0
        //getRawY() is absolute Y location from left-top = 0

        //in activity_popup.xml
        //layoutWidth and layoutHeight
        //must set to match_parent,or otherwise it has no space to move
        windowView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                //for precisely locate the window
                //var x:Float? = 0f
                //var y:Float? = 0f

                when (event?.action) {
                    MotionEvent.ACTION_DOWN or MotionEvent.ACTION_MOVE -> {
                        params.x = event.getRawX().toInt() - (width / 2).toInt()
                        params.y = event.getRawY().toInt() - (windowHeight / 2).toInt()
                        getWindow().setAttributes(params)
                        /* //for precisely locate the window
                       x=event.getRawX()

                       y=event.getRawY()
                       Log.d(TAG,"Action_down x: $x y:$y")
                       Log.d(TAG," w: $width h:$height")
                      */
                    }
                    //must add this by myself so that it can work........
                    null -> {
                        return false
                    }

                }
                // why can't use this?
                // return v?.onTouchEvent(event) ?: false

                //must add this by myself so that it can work........
                return true
            }
        })

        var calnumInput:Float = 1f
        var calnumInput1:Float = 1f
        var caldenInput:Float = 1f
        var caldenInput1:Float = 1f
        var caltaxInput:Float = 0f

        fun calResult(){
            var calResult = (calnumInput*calnumInput1*((100+caltaxInput)/100)/(caldenInput*caldenInput1))
            val calResultText:TextView = findViewById(R.id.calResult)
            calResultText.text = calResult.toString()
        }

        calnumInputtext.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var calnumInputNable = s.toString().toFloatOrNull()

                if(calnumInputNable==null){
                    calnumInput = 1f
                }
                else{
                    calnumInput = calnumInputNable
                }
                calResult()
            }
            override fun afterTextChanged(e: Editable) {}
        })

        calnumInputtext1.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var calnumInputNable = s.toString().toFloatOrNull()

                if(calnumInputNable==null){
                    calnumInput1 = 1f
                }
                else{
                    calnumInput1 = calnumInputNable
                }
                calResult()
            }
            override fun afterTextChanged(e: Editable) {}
        })

        caldenInputtext.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var caldenInputNable = s.toString().toFloatOrNull()

                if(caldenInputNable==null){
                    caldenInput = 1f
                }
                else{
                    caldenInput = caldenInputNable
                }
                calResult()
            }
            override fun afterTextChanged(e: Editable) {}
        })

        caldenInputtext1.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var caldenInputNable = s.toString().toFloatOrNull()

                if(caldenInputNable==null){
                    caldenInput1 = 1f
                }
                else{
                    caldenInput1 = caldenInputNable
                }
                calResult()

            }
            override fun afterTextChanged(e: Editable) {}
        })

        calTaxinputtext.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var caltaxInputNable = s.toString().toFloatOrNull()

                if(caltaxInputNable==null){
                    caltaxInput = 0f
                }
                else{
                    caltaxInput = caltaxInputNable
                }
                calResult()
            }
            override fun afterTextChanged(e: Editable) {}
        })

    }
}
