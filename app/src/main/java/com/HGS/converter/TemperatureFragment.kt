package com.HGS.converter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_temperature.*

class TemperatureFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_temperature, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_F2C.setOnClickListener { v -> tempF2C(v) }

        tempFText.setOnKeyListener { v, keyCode, event ->
                /*var inputTempInputF = tempFText.text.toString().toInt()
                var tempOutputC = (inputTempInputF-32)*5/9
                button_F2C.text = tempOutputC.toString()
                */
                 false
            }
        }


    fun tempF2C(v: View) {
        var inputTempInputF = tempFText.text.toString().toFloat()
        var OutputTempC = (inputTempInputF-32)*5/9
        val StringOutputTempC = OutputTempC.toString()
        val F2Cresult = getString(R.string.CelsiusResult,StringOutputTempC)
        Celsius.text =  F2Cresult
    }
}