package com.HGS.converter

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.fragment_temperature.*

class TemperatureFragment : Fragment() {
    fun Float.f2c(): Float {
        return (this-32)*5/9
    }
    fun Float.c2f(): Float {
        return this*9/5+32
    }

    fun Float.celsiusText(): String {
        return getString(R.string.CelsiusResult, this)
    }

    fun Float.FahrenheitText(): String {
        return getString(R.string.FahrenheitResult, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_temperature, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        val Inputspinner:Spinner = tempInputSpinner
        var tempItemSelect = 0

        ArrayAdapter.createFromResource(
            this.activity,
            R.array.tempItem_Array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Inputspinner.adapter = adapter
        }

        Inputspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tempItemSelect = position
                tempInputText.setText("")
                when(tempItemSelect)
                {
                    0 -> {
                        ID_tempFormula.text = getString(R.string.tempF2CFormula)
                        tempInputText.hint=getString(R.string.Fahrenheit)
                    }
                    1 -> {
                        ID_tempFormula.text = getString(R.string.tempC2FFormula)
                        tempInputText.hint=getString(R.string.Celsius)
                    }
                }
            }

        }



        tempInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when(tempItemSelect){
                    0 -> textTempResult.text = s.toString().toFloatOrNull()?.f2c()?.celsiusText() ?: getString(R.string.FInputError)
                    1 -> textTempResult.text = s.toString().toFloatOrNull()?.c2f()?.FahrenheitText() ?: getString(R.string.CInputError)
                }
            }
            override fun afterTextChanged(e: Editable) {}
        })
    }
}