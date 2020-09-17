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
import kotlinx.android.synthetic.main.fragment_volume.*
// 1.If user touch any of the inputtext, will trigger "-----InputText.setOnTouchListener"
//   this will clear all of the inputtext
// 2.If user input value into any of the inputtext, will trigger "----InputText.addTextChangedListener "
//   this will tell capacityCalculateAndShow(s,capacityInputUnit) who it is by capacityInputUnit

class VolumeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_volume, container,false)
    }
    enum class capacityList{
        teaspoon,
        tablespoon,
        floz,
        cup,
        pint,
        quart,
        gallon,
        ml,
        l
    }

    fun clearAllEditText(){
        teaSpoonInputText.setText("")
        tableSpoonInputText.setText("")
        flozInputText.setText("")
        cupInputText.setText("")
        pintInputText.setText("")
        quartInputText.setText("")
        gallonInputText.setText("")
        mlInputText.setText("")
        lInputText.setText("")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var capacityInputUnit: Int = 0
        // This function will
        // 1. first convert all "Input unit" into sqft
        // 2. calculate coefficient between "teaspoon" to all other "output unit"
        // 3. calculate result and show

        fun capacityCalculateAndShow(s: CharSequence?,areaInputUnit:Int) {
            var capacityInputValue = s.toString().toFloatOrNull()
            var input2teaspoon = 0f
            //first convert all "Input unit" into teaspoon
            when (capacityInputUnit) {
                capacityList.teaspoon.ordinal -> input2teaspoon = 1f
                capacityList.tablespoon.ordinal -> input2teaspoon = 3f
                capacityList.floz.ordinal -> input2teaspoon = 6f
                capacityList.cup.ordinal -> input2teaspoon = 48f
                capacityList.pint.ordinal -> input2teaspoon = 96f
                capacityList.quart.ordinal -> input2teaspoon = 192f
                capacityList.gallon.ordinal -> input2teaspoon = 768f
                capacityList.ml.ordinal -> input2teaspoon = 0.20288413f
                capacityList.l.ordinal -> input2teaspoon = 202.88413f
            }
            //calculate coefficient between "teaspoon" to all other "output unit"
            val teaspoon2tablespoon = input2teaspoon / 3f
            val teaspoon2floz = input2teaspoon / 6f
            val teaspoon2cup = input2teaspoon / 48f
            val teaspoon2pint = input2teaspoon / 96f
            val teaspoon2quart = input2teaspoon / 192f
            val teaspoon2gallon = input2teaspoon / 768f
            val teaspoon2ml = input2teaspoon / 0.20288413f
            val teaspoon2l = input2teaspoon / 202.88413f
            // calculate and show the result
            if(capacityInputUnit!= capacityList.teaspoon.ordinal){
                if(capacityInputValue==null){
                    teaSpoonInputText.hint=getString(R.string.teaSpoon)
                }
                else{
                    teaSpoonInputText.hint=(capacityInputValue*input2teaspoon).toString()
                }
            }
            if(capacityInputUnit!= capacityList.tablespoon.ordinal){
                if(capacityInputValue==null){
                    tableSpoonInputText.hint=getString(R.string.tableSpoon)
                }
                else{
                    tableSpoonInputText.hint=(capacityInputValue*teaspoon2tablespoon).toString()
                }
            }
            if(capacityInputUnit!= capacityList.floz.ordinal){
                if(capacityInputValue==null){
                    flozInputText.hint=getString(R.string.floz)
                }
                else{
                    flozInputText.hint=(capacityInputValue*teaspoon2floz).toString()
                }
            }
            if(capacityInputUnit!= capacityList.cup.ordinal){
                if(capacityInputValue==null){
                    cupInputText.hint=getString(R.string.cup)
                }
                else{
                    cupInputText.hint=(capacityInputValue*teaspoon2cup).toString()
                }
            }
            if(capacityInputUnit!= capacityList.pint.ordinal){
                if(capacityInputValue==null){
                    pintInputText.hint=getString(R.string.pint)
                }
                else{
                    pintInputText.hint=(capacityInputValue*teaspoon2pint).toString()
                }
            }
            if(capacityInputUnit!= capacityList.quart.ordinal){
                if(capacityInputValue==null){
                    quartInputText.hint=getString(R.string.quart)
                }
                else{
                    quartInputText.hint=(capacityInputValue*teaspoon2quart).toString()
                }
            }
            if(capacityInputUnit!= capacityList.gallon.ordinal){
                if(capacityInputValue==null){
                    gallonInputText.hint=getString(R.string.gallon)
                }
                else{
                    gallonInputText.hint=(capacityInputValue*teaspoon2gallon).toString()
                }
            }
            if(capacityInputUnit!= capacityList.ml.ordinal){
                if(capacityInputValue==null){
                    mlInputText.hint=getString(R.string.ml)
                }
                else{
                    mlInputText.hint=(capacityInputValue*teaspoon2ml).toString()
                }
            }
            if(capacityInputUnit!= capacityList.l.ordinal){
                if(capacityInputValue==null){
                    lInputText.hint=getString(R.string.l)
                }
                else{
                    lInputText.hint=(capacityInputValue*teaspoon2l).toString()
                }
            }
        }
        //Edit Text "On Touch" and "On Key" listeners
        //teaspoon
        teaSpoonInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->clearAllEditText()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        teaSpoonInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                capacityInputUnit = capacityList.teaspoon.ordinal
                capacityCalculateAndShow(s,capacityInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })
        //tablespoon
        tableSpoonInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->clearAllEditText()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        tableSpoonInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                capacityInputUnit = capacityList.tablespoon.ordinal
                capacityCalculateAndShow(s,capacityInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })

        //floz
        flozInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->clearAllEditText()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        flozInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                capacityInputUnit = capacityList.floz.ordinal
                capacityCalculateAndShow(s,capacityInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })
        //cup
        cupInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->clearAllEditText()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        cupInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                capacityInputUnit = capacityList.cup.ordinal
                capacityCalculateAndShow(s,capacityInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })
        //pint
        pintInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->clearAllEditText()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        pintInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                capacityInputUnit = capacityList.pint.ordinal
                capacityCalculateAndShow(s,capacityInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })
        //quart
        quartInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->clearAllEditText()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        quartInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                capacityInputUnit = capacityList.quart.ordinal
                capacityCalculateAndShow(s,capacityInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })
        //gallon
        gallonInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->clearAllEditText()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        gallonInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                capacityInputUnit = capacityList.gallon.ordinal
                capacityCalculateAndShow(s,capacityInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })
        //ml
        mlInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->clearAllEditText()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        mlInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                capacityInputUnit = capacityList.ml.ordinal
                capacityCalculateAndShow(s,capacityInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })
        //l
        lInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->clearAllEditText()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        lInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                capacityInputUnit = capacityList.l.ordinal
                capacityCalculateAndShow(s,capacityInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })
    }
}