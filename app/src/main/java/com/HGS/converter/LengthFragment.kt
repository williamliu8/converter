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
import kotlinx.android.synthetic.main.fragment_length.*

class LengthFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_length, container,false)
    }

    enum class lengthList{
        inch,
        feet,
        yard,
        mile,
        cm,
        m,
        km
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val lengthArray = getResources().getStringArray(R.array.lengthItem_Array)
        var coefficient: Float = 0f
        //Input Spinner
        val Inputspinner: Spinner = lengthInputSpinner
        var lengthInputItemSelect = 0
        //Output Spinner
        val Outputspinner: Spinner = lengthOutputSpinner
        var lengthOutputItemSelect = 0

        ArrayAdapter.createFromResource(
            this.activity,
            R.array.lengthItem_Array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Inputspinner.adapter = adapter
        }

        fun lengthFindCoefficient(Inch:Float,OutputSelect:Int){
            var CM:Float=Inch*2.54f
            when(OutputSelect){
                lengthList.inch.ordinal -> coefficient = Inch
                lengthList.feet.ordinal -> coefficient = Inch/12
                lengthList.yard.ordinal -> coefficient = Inch/36
                lengthList.mile.ordinal -> coefficient = Inch/63360
                lengthList.cm.ordinal -> coefficient = CM
                lengthList.m.ordinal -> coefficient = CM/100
                lengthList.km.ordinal -> coefficient = CM/100000
            }
        }

        fun lengthInputOutputHandler(InputSelect:Int,OutputSelect:Int){
            var InputEQ2Inch = 0f
            when(InputSelect)
            {
                lengthList.inch.ordinal -> {
                    InputEQ2Inch = 1f
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
                lengthList.feet.ordinal ->{
                    InputEQ2Inch = 12f
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
                lengthList.yard.ordinal ->{
                    InputEQ2Inch = 36f
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
                lengthList.mile.ordinal ->{
                    InputEQ2Inch=63360f
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
                lengthList.cm.ordinal ->{
                    InputEQ2Inch=1/2.54f
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
                lengthList.m.ordinal ->{
                    InputEQ2Inch=100/2.54f
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
                lengthList.km.ordinal ->{
                    InputEQ2Inch=100000/2.54f
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
            }

        }
        Inputspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                lengthInputText.setText("")
                lengthInputItemSelect = position
                lengthInputText.hint=lengthArray[lengthInputItemSelect]
                lengthInputOutputHandler(lengthInputItemSelect,lengthOutputItemSelect)
                ID_lengthFormula.text=getString(R.string.textFormula,lengthArray[lengthInputItemSelect],coefficient,lengthArray[lengthOutputItemSelect])
            }
        }

        //Output Spinner
        ArrayAdapter.createFromResource(
            this.activity,
            R.array.lengthItem_Array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Outputspinner.adapter = adapter
        }

        Outputspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                lengthInputText.setText("")
                lengthOutputItemSelect = position
                lengthInputOutputHandler(lengthInputItemSelect,lengthOutputItemSelect)
                ID_lengthFormula.text=getString(R.string.textFormula,lengthArray[lengthInputItemSelect],coefficient,lengthArray[lengthOutputItemSelect])

            }
        }

        fun Float.lengthTrans(coefficient:Float): Float {
            return this*coefficient
        }

        fun Float.lengthResultText(OutputItem:Int,InputItem:Int,lengthArray:Array<String>): String {
            var resultString:Int
            if(this<=0.001 && this > 0){
                resultString = R.string.textResultTooSmall
                return getString(resultString,lengthArray[InputItem],lengthArray[OutputItem])
            }
            else{
                resultString = R.string.textResult
                return getString(resultString,lengthArray[InputItem],this,lengthArray[OutputItem])
            }

        }

        lengthInputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textLengthResult.text = s.toString().toFloatOrNull()?.lengthTrans(coefficient)?.lengthResultText(lengthOutputItemSelect,lengthInputItemSelect,lengthArray) ?: getString(R.string.lengthInputError)
            }
            override fun afterTextChanged(e: Editable) {}
        })



    }
}