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


        fun weightFindCoefficient(Ounce:Float,OutputSelect:Int) {
            var gram=Ounce*28.349523125f
            when(OutputSelect){
                weightList.ounce.ordinal -> coefficient = Ounce
                weightList.pound.ordinal -> coefficient = Ounce/16
                weightList.uston.ordinal -> coefficient = Ounce/32000
                weightList.metricton.ordinal -> coefficient = gram/1000000
                weightList.Kg.ordinal -> coefficient = gram/1000f
                weightList.g.ordinal -> coefficient = gram
            }
        }

        fun weightInputOutputHandler(InputSelect:Int,OutputSelect:Int){
            var InputEQ2ounce = 0f
            when(InputSelect)
            {
                weightList.ounce.ordinal -> {
                    InputEQ2ounce = 1f
                    weightFindCoefficient(InputEQ2ounce,OutputSelect)
                }
                weightList.pound.ordinal ->{
                    InputEQ2ounce = 16f
                    weightFindCoefficient(InputEQ2ounce,OutputSelect)
                }
                weightList.uston.ordinal -> {
                    InputEQ2ounce = 32000f
                    weightFindCoefficient(InputEQ2ounce,OutputSelect)
                }
                weightList.metricton.ordinal -> {
                    InputEQ2ounce = 35273.9619f
                    weightFindCoefficient(InputEQ2ounce,OutputSelect)
                }
                weightList.Kg.ordinal -> {
                    InputEQ2ounce = 35.2739619f
                    weightFindCoefficient(InputEQ2ounce,OutputSelect)
                }
                weightList.g.ordinal -> {
                    InputEQ2ounce = 0.0352739619f
                    weightFindCoefficient(InputEQ2ounce,OutputSelect)
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
                ID_weightFormula.text=getString(R.string.textFormula,weightArray[weightInputItemSelect],coefficient,weightArray[weightOutputItemSelect])
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
                ID_weightFormula.text=getString(R.string.textFormula,weightArray[weightInputItemSelect],coefficient,weightArray[weightOutputItemSelect])

            }
        }

        fun Float.weightTrans(coefficient:Float): Float {
            return this*coefficient
        }

        fun Float.weightResultText(OutputItem:Int,InputItem:Int,weightArray:Array<String>): String {
            var resultString:Int
            if(this<=0.001 && this > 0){
                resultString = R.string.textResultTooSmall
                return getString(resultString,weightArray[InputItem],weightArray[OutputItem])
            }
            else{
                resultString = R.string.textResult
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