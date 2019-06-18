package com.HGS.converter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_area.*
import kotlinx.android.synthetic.main.fragment_area.view.*

class AreaFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_area, container,false)
    }

    enum class areaList{
        sqft,
        ping,
        sqmeter,
        hectare,
        macre,
        acre
    }

    fun clearAllEditText(){
        sqftInputText.setText("")
        pingInputText.setText("")
        sqMeterInputText.setText("")
        hectareInputText.setText("")
        macreInputText.setText("")
        acreInputText.setText("")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var areaInputUnit:Int=0

        fun areaCalculateAndShow(s: CharSequence?,areaInputUnit:Int){
            var areaInputValue=s.toString().toFloatOrNull()
            var input2sqft=0f

            when(areaInputUnit){
                areaList.sqft.ordinal -> input2sqft = 1f
                areaList.ping.ordinal -> input2sqft = 35.58303f
                areaList.sqmeter.ordinal -> input2sqft = 10.7638675f
                areaList.hectare.ordinal -> input2sqft = 107638.675f
                areaList.macre.ordinal -> input2sqft = 1076.38675f
                areaList.acre.ordinal -> input2sqft = 43560f
            }

            val sqft2acre = input2sqft/43560f
            val sqft2sqmeter = input2sqft/10.7638675f
            val sqft2ping = sqft2sqmeter/3.305785f
            val sqft2hectare = sqft2sqmeter/10000f
            val sqft2macre = sqft2sqmeter/100f

            if(areaInputUnit!= areaList.sqft.ordinal){
                if(areaInputValue==null){
                    sqftInputText.hint=getString(R.string.sqFeet)
                }
                else{
                    sqftInputText.hint=(areaInputValue*input2sqft).toString()
                }
            }
            if(areaInputUnit!= areaList.ping.ordinal){
                if(areaInputValue==null){
                    pingInputText.hint=getString(R.string.Ping)
                }
                else{
                    pingInputText.hint=(areaInputValue*sqft2ping).toString()
                }
            }
            if(areaInputUnit!= areaList.sqmeter.ordinal){
                if(areaInputValue==null){
                    sqMeterInputText.hint=getString(R.string.sqMeter)
                }
                else{
                    sqMeterInputText.hint=(areaInputValue*sqft2sqmeter).toString()
                }
            }
            if(areaInputUnit!= areaList.hectare.ordinal){
                if(areaInputValue==null){
                    hectareInputText.hint=getString(R.string.Hectare)
                }
                else{
                    hectareInputText.hint=(areaInputValue*sqft2hectare).toString()
                }
            }
            if(areaInputUnit!= areaList.macre.ordinal){
                if(areaInputValue==null){
                    macreInputText.hint=getString(R.string.MetricAcre)
                }
                else{
                    macreInputText.hint=(areaInputValue*sqft2macre).toString()
                }
            }
            if(areaInputUnit!= areaList.acre.ordinal){
                if(areaInputValue==null){
                    acreInputText.hint=getString(R.string.Acre)
                }
                else{
                    acreInputText.hint=(areaInputValue*sqft2acre).toString()
                }
            }
        }


        //Edit Text "On Touch" and "On Key" listeners
            //sqft
            sqftInputText.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN ->clearAllEditText()
                    }

                    return v?.onTouchEvent(event) ?: true
                }
            })

            sqftInputText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    areaInputUnit = areaList.sqft.ordinal
                    areaCalculateAndShow(s,areaInputUnit)
                }
                override fun afterTextChanged(e: Editable) {}
            })
            //ping
            pingInputText.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN ->clearAllEditText()
                    }
                    return v?.onTouchEvent(event) ?: true
                }
            })

            pingInputText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    areaInputUnit = areaList.ping.ordinal
                    areaCalculateAndShow(s,areaInputUnit)
                }
                override fun afterTextChanged(e: Editable) {}
            })
            //sqMeter
            sqMeterInputText.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN ->clearAllEditText()
                    }
                    return v?.onTouchEvent(event) ?: true
                }
            })

            sqMeterInputText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    areaInputUnit = areaList.sqmeter.ordinal
                    areaCalculateAndShow(s,areaInputUnit)
                }
                override fun afterTextChanged(e: Editable) {}
            })
            //hectare
            hectareInputText.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN ->clearAllEditText()
                    }

                    return v?.onTouchEvent(event) ?: true
                }
            })

            hectareInputText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    areaInputUnit = areaList.hectare.ordinal
                    areaCalculateAndShow(s,areaInputUnit)
                }
                override fun afterTextChanged(e: Editable) {}
            })
            //macre
            macreInputText.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN ->clearAllEditText()
                    }
                    return v?.onTouchEvent(event) ?: true
                }
            })

            macreInputText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    areaInputUnit = areaList.macre.ordinal
                    areaCalculateAndShow(s,areaInputUnit)
                }
                override fun afterTextChanged(e: Editable) {}
            })
            //acre
            acreInputText.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN ->clearAllEditText()
                    }
                    return v?.onTouchEvent(event) ?: true
                }
            })

            acreInputText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    areaInputUnit = areaList.acre.ordinal
                    areaCalculateAndShow(s,areaInputUnit)
                }
                override fun afterTextChanged(e: Editable) {}
            })
    }
}