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

enum class tempList{
    F2C,
    C2F
}

class TemperatureFragment : Fragment() {
    fun Float.tempTrans(tempTransType:Int): Float {
        var tempResult:Float = 0f
        when(tempTransType){
            tempList.F2C.ordinal -> tempResult=(this-32)*5/9
            tempList.C2F.ordinal -> tempResult=this*9/5+32
        }
        return tempResult
    }

    fun Float.tempResultText(tempTransType:Int): String {
        var tempInput:String = ""
        var tempOutput:String =""
        when(tempTransType){
            tempList.F2C.ordinal->{
                tempInput = getString(R.string.tempFdegree)
                tempOutput = getString(R.string.tempCdegree)
            }
            tempList.C2F.ordinal->{
                tempInput = getString(R.string.tempCdegree)
                tempOutput = getString(R.string.tempFdegree)
            }

        }
        return getString(R.string.tempResult,tempInput,this,tempOutput)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_temperature, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
                    tempList.F2C.ordinal -> {
                        ID_tempFormula.text = getString(R.string.tempF2CFormula)
                        tempInputText.hint=getString(R.string.tempFdegree)
                    }
                    tempList.C2F.ordinal -> {
                        ID_tempFormula.text = getString(R.string.tempC2FFormula)
                        tempInputText.hint=getString(R.string.tempCdegree)
                    }
                }
            }

        }



        tempInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textTempResult.text = s.toString().toFloatOrNull()?.tempTrans(tempItemSelect)?.tempResultText(tempItemSelect) ?: getString(R.string.tempInputError)
            }
            override fun afterTextChanged(e: Editable) {}
        })
    }
}