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
        // a global variable
        var coefficient: Float = 0f
        //Input Spinner
        val Inputspinner: Spinner = lengthInputSpinner
        var lengthInputItemSelect = 0
        //Output Spinner
        val Outputspinner: Spinner = lengthOutputSpinner
        var lengthOutputItemSelect = 0
        //create an adapter for input spinner
        //see https://developer.android.com/guide/topics/ui/controls/spinner#kotlin
        ArrayAdapter.createFromResource(
            this.activity,
            R.array.lengthItem_Array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Inputspinner.adapter = adapter
        }

        // we've already convert all "input unit" into Inch
        // we will update coefficient(a global variable) according to "output unit"
        fun lengthFindCoefficient(Inch:Float,OutputSelect:Int){
            var CM:Float=Inch*2.54f // convert Inch to CM here
            when(OutputSelect){
                lengthList.inch.ordinal -> coefficient = Inch // if output is inch, coefficient = inch (inch to inch)
                lengthList.feet.ordinal -> coefficient = Inch/12 // if output is feet, coefficient = inch/12(inch to feet)
                lengthList.yard.ordinal -> coefficient = Inch/36 // if output is yard, coefficient = inch/36(inch to yard)
                lengthList.mile.ordinal -> coefficient = Inch/63360 // if output is mile, coefficient = inch/63360(inch to mile)
                lengthList.cm.ordinal -> coefficient = CM  // if output is CM, coefficient = CM (CM to CM)
                lengthList.m.ordinal -> coefficient = CM/100 // if output is M, coefficient = CM/100 (CM to M)
                lengthList.km.ordinal -> coefficient = CM/100000 // if output is KM, coefficient = CM/100000 (CM to KM)
            }
        }
        // this function will:
        // 1.convert "input unit" into "Inch"
        // 2.find the convert coefficient between "output unit"
        fun lengthInputOutputHandler(InputSelect:Int,OutputSelect:Int){
            var InputEQ2Inch = 0f
            when(InputSelect)
            {
                lengthList.inch.ordinal -> {
                    InputEQ2Inch = 1f // 1 inch = 1 inch
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
                lengthList.feet.ordinal ->{
                    InputEQ2Inch = 12f // 1 feet = 12 inch
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
                lengthList.yard.ordinal ->{
                    InputEQ2Inch = 36f  // 1 yard = 36 inch
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
                lengthList.mile.ordinal ->{
                    InputEQ2Inch=63360f  // 1 mile = 63360 inch
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
                lengthList.cm.ordinal ->{
                    InputEQ2Inch=1/2.54f // 1cm = 1/2.54 inch
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
                lengthList.m.ordinal ->{
                    InputEQ2Inch=100/2.54f // 1M = 100/2.54 inch
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
                lengthList.km.ordinal ->{
                    InputEQ2Inch=100000/2.54f // 1KM = 100000/2.54 inch
                    lengthFindCoefficient(InputEQ2Inch,OutputSelect)
                }
            }

        }
        // when select input:
        // formula will show : input = coefficient output
        // input text will show input
        // update coefficient(global variable) between input and output unit
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
        //create an adapter for output spinner
        ArrayAdapter.createFromResource(
            this.activity,
            R.array.lengthItem_Array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Outputspinner.adapter = adapter
        }
        // when select output:
        // formula will show : input = coefficient output
        // update coefficient(global variable) between input and output unit

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
        // this function returns "input text" * coefficient
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