package com.HGS.converter

import android.icu.util.Output
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_temperature.*

class TemperatureFragment : Fragment() {
    data class F2CDataClass(var Input: Float, var Output:Float,var Result:String) {
    }
    var F2CData = F2CDataClass(0f,0f,"0")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_temperature, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initialtextF2CResult()
        button_F2C.setOnClickListener { v -> tempF2C(v) }

        tempFText.setOnKeyListener { v, keyCode, event ->
                /*var inputTempInputF = tempFText.text.toString().toInt()
                var tempOutputC = (inputTempInputF-32)*5/9
                button_F2C.text = tempOutputC.toString()
                */
                 false
            }
        }

    fun initialtextF2CResult(){
        F2CData.Result = getString(R.string.CelsiusResult,F2CData.Result)
        textF2CResult.text =  F2CData.Result
    }

    fun tempF2C(v: View) {
        F2CData.Input = tempFText.text.toString().toFloat()
        F2CData.Output = (F2CData.Input-32)*5/9
        F2CData.Result = getString(R.string.CelsiusResult,F2CData.Output.toString())
        textF2CResult.text = F2CData.Result
    }
}