package com.HGS.converter

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
import kotlinx.android.synthetic.main.fragment_weight.*

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val weightArray = getResources().getStringArray(R.array.weightItem_Array)
        var coefficient:Float= 0f
        //Input Spinner
        val Inputspinner: Spinner = weightInputSpinner
        var weightInputItemSelect = 0
        //Output Spinner
        val Outputspinner: Spinner = weightOutputSpinner
        var weightOutputItemSelect = 0


        ArrayAdapter.createFromResource(
            this.activity,
            R.array.weightItem_Array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Inputspinner.adapter = adapter
        }

        fun weightInputOutputHandler(InputSelect:Int,OutputSelect:Int){
            when(InputSelect)
            {
                weightList.ounce.ordinal -> {
                    when(OutputSelect){
                        weightList.ounce.ordinal -> coefficient = 1f
                        weightList.pound.ordinal -> coefficient = 0.0625f
                        weightList.uston.ordinal -> coefficient = 0.000032f
                        weightList.metricton.ordinal -> coefficient = 0.0000283f
                        weightList.Kg.ordinal -> coefficient = 0.0283f
                        weightList.g.ordinal -> coefficient = 28.3f
                    }
                }
                weightList.pound.ordinal ->{
                    when(OutputSelect){
                        weightList.ounce.ordinal -> coefficient = 16f
                        weightList.pound.ordinal -> coefficient = 1f
                        weightList.uston.ordinal -> coefficient = 0.0005f
                        weightList.metricton.ordinal -> coefficient = 0.000453f
                        weightList.Kg.ordinal -> coefficient = 0.45359f
                        weightList.g.ordinal -> coefficient = 453.59f
                    }
                }
                weightList.uston.ordinal -> {
                    when(OutputSelect){
                        weightList.ounce.ordinal -> coefficient = 32000f
                        weightList.pound.ordinal -> coefficient = 2000f
                        weightList.uston.ordinal -> coefficient = 1f
                        weightList.metricton.ordinal -> coefficient = 0.907184f
                        weightList.Kg.ordinal -> coefficient = 907.184f
                        weightList.g.ordinal -> coefficient = 907184f
                    }
                }
                weightList.metricton.ordinal -> {
                    when(OutputSelect){
                        weightList.ounce.ordinal -> coefficient = 35273.99f
                        weightList.pound.ordinal -> coefficient = 2204.624f
                        weightList.uston.ordinal -> coefficient = 1.1023f
                        weightList.metricton.ordinal -> coefficient = 1f
                        weightList.Kg.ordinal -> coefficient = 1000f
                        weightList.g.ordinal -> coefficient = 1000000f
                    }
                }
                weightList.Kg.ordinal -> {
                    when(OutputSelect){
                        weightList.ounce.ordinal -> coefficient = 35.274f
                        weightList.pound.ordinal -> coefficient = 2.204f
                        weightList.uston.ordinal -> coefficient = 0.0011f
                        weightList.metricton.ordinal -> coefficient = 1000f
                        weightList.Kg.ordinal -> coefficient = 1f
                        weightList.g.ordinal -> coefficient = 1000f
                    }
                }
                weightList.g.ordinal -> {
                    when(OutputSelect){
                        weightList.ounce.ordinal -> coefficient = 0.0352f
                        weightList.pound.ordinal -> coefficient = 0.0022f
                        weightList.uston.ordinal -> coefficient = 0.0011f
                        weightList.metricton.ordinal -> coefficient = 0.000001f
                        weightList.Kg.ordinal -> coefficient = 0.001f
                        weightList.g.ordinal -> coefficient = 1f
                    }
                }
            }

        }

        Inputspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                weightInputText.setText("")
                weightInputItemSelect = position
                weightInputText.hint=weightArray[weightInputItemSelect]
                weightInputOutputHandler(weightInputItemSelect,weightOutputItemSelect)
                ID_weightFormula.text=getString(R.string.weightFormula,weightArray[weightInputItemSelect],coefficient,weightArray[weightOutputItemSelect])
            }

        }
        //Output Spinner
        ArrayAdapter.createFromResource(
            this.activity,
            R.array.weightItem_Array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Outputspinner.adapter = adapter
        }

        Outputspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                weightInputText.setText("")
                weightOutputItemSelect = position
                weightInputOutputHandler(weightInputItemSelect,weightOutputItemSelect)
                ID_weightFormula.text=getString(R.string.weightFormula,weightArray[weightInputItemSelect],coefficient,weightArray[weightOutputItemSelect])

            }
        }

        fun Float.weightTrans(coefficient:Float): Float {
            return this*coefficient
        }

        fun Float.weightResultText(OutputItem:Int,InputItem:Int,weightArray:Array<String>): String {
            var resultString:Int
            if(this<=0.001 && this > 0){
                resultString = R.string.weightResultTooSmall
                return getString(resultString,weightArray[InputItem],weightArray[OutputItem])
            }
            else{
                resultString = R.string.weightResult
                return getString(resultString,weightArray[InputItem],this,weightArray[OutputItem])
            }

        }

        weightInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textWeightResult.text = s.toString().toFloatOrNull()?.weightTrans(coefficient)?.weightResultText(weightOutputItemSelect,weightInputItemSelect,weightArray) ?: getString(R.string.weightInputError)
            }
            override fun afterTextChanged(e: Editable) {}
        })



    }
}