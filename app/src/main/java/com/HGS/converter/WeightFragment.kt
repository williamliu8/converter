package com.HGS.converter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_weight.*
import java.math.RoundingMode
import java.text.DecimalFormat

class WeightFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weight, container,false)
    }

    enum class weightList{
        ounce,
        pound,
        uston,
        metricton,
        Kg,
        g
    }

    fun clearAllEditText(){
        ounceInputText.setText("")
        poundInputText.setText("")
        ustonInputText.setText("")
        metrictonInputText.setText("")
        KgInputText.setText("")
        gInputText.setText("")
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var weightInputUnit: Int = 0

        fun weightCalculateAndShow(s: CharSequence?, weightInputUnit: Int) {
            var weightInputValue = s.toString().toFloatOrNull()
            var input2g = 0f

            when (weightInputUnit) {
                weightList.ounce.ordinal -> input2g = 28.349523125f
                weightList.pound.ordinal -> input2g = 453.59237f
                weightList.uston.ordinal -> input2g = 907184.74f
                weightList.g.ordinal -> input2g = 1f
                weightList.Kg.ordinal -> input2g = 1000f
                weightList.metricton.ordinal -> input2g = 1000000f
            }

            val g2ounce = input2g/28.349523125f
            val g2Kg = input2g/1000f
            val g2metricton = input2g/1000000f
            val g2pound = input2g / 453.59237f
            val g2uston = input2g / 907184.74f
/*
            fun round2thousandth(num:Float,mode:Int):String{
                val df = DecimalFormat("#.######")
                when(mode){
                    0 -> df.roundingMode = RoundingMode.FLOOR
                    1 -> df.roundingMode = RoundingMode.CEILING
                }

                return df.format(num)
            }
*/
            if (weightInputUnit != weightList.ounce.ordinal) {
                if (weightInputValue == null) {
                    ounceInputText.hint = getString(R.string.ounce)
                } else {
                    ounceInputText.hint = (weightInputValue * g2ounce).toString()
                }
            }

            if (weightInputUnit != weightList.g.ordinal) {
                if (weightInputValue == null) {
                    gInputText.hint = getString(R.string.g)
                } else {
                    gInputText.hint = (weightInputValue * input2g).toString()
                }
            }

            if (weightInputUnit != weightList.pound.ordinal) {
                if (weightInputValue == null) {
                    poundInputText.hint = getString(R.string.pound)
                } else {
                    poundInputText.hint = (weightInputValue * g2pound).toString()
                }
            }
            if (weightInputUnit != weightList.Kg.ordinal) {
                if (weightInputValue == null) {
                    KgInputText.hint = getString(R.string.kg)
                } else {
                    KgInputText.hint = (weightInputValue * g2Kg).toString()
                }
            }
            if (weightInputUnit != weightList.metricton.ordinal) {
                if (weightInputValue == null) {
                    metrictonInputText.hint = getString(R.string.metricton)
                } else {
                    metrictonInputText.hint = (weightInputValue * g2metricton).toString()
                }
            }
            if (weightInputUnit != weightList.uston.ordinal) {
                if (weightInputValue == null) {
                    ustonInputText.hint = getString(R.string.uston)
                } else {
                    ustonInputText.hint = (weightInputValue * g2uston).toString()
                }
            }
        }

        //Edit Text "On Touch" and "On Key" listeners
        //ounce
        ounceInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> clearAllEditText()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        ounceInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
               override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                   weightInputUnit = weightList.ounce.ordinal
                   weightCalculateAndShow(s, weightInputUnit)
               }

            override fun afterTextChanged(e: Editable) {}
        })
        //g
        gInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> clearAllEditText()
                }
                return v?.onTouchEvent(event) ?: true
            }
        })

        gInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                weightInputUnit = weightList.g.ordinal
                weightCalculateAndShow(s, weightInputUnit)
            }

            override fun afterTextChanged(e: Editable) {}
        })
        //pound
        poundInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> clearAllEditText()
                }
                return v?.onTouchEvent(event) ?: true
            }
        })

        poundInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                weightInputUnit = weightList.pound.ordinal
                weightCalculateAndShow(s, weightInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })
        //Kg
        KgInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> clearAllEditText()
                }
                return v?.onTouchEvent(event) ?: true
            }
        })

        KgInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                weightInputUnit = weightList.Kg.ordinal
                weightCalculateAndShow(s, weightInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })
        //uston
        ustonInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> clearAllEditText()
                }
                return v?.onTouchEvent(event) ?: true
            }
        })

        ustonInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                weightInputUnit = weightList.uston.ordinal
                weightCalculateAndShow(s, weightInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })
        //metricton
        metrictonInputText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> clearAllEditText()
                }
                return v?.onTouchEvent(event) ?: true
            }
        })

        metrictonInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                weightInputUnit = weightList.metricton.ordinal
                weightCalculateAndShow(s, weightInputUnit)
            }
            override fun afterTextChanged(e: Editable) {}
        })
    }
}