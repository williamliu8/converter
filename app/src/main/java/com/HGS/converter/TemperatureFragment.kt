package com.HGS.converter

import android.icu.util.Output
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

    fun Float.celsiusText(): String {
        return getString(R.string.CelsiusResult, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_temperature, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val Inputspinner:Spinner = tempInputSpinner

        ArrayAdapter.createFromResource(
            this.activity,
            R.array.temItem_Array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Inputspinner.adapter = adapter
        }

        Inputspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ID_c2F.text = Inputspinner.getSelectedItem().toString()
            }

        }



        tempFText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textF2CResult.text = s.toString()
                    .toFloatOrNull()
                    ?.f2c()
                    ?.celsiusText() ?: getString(R.string.CelsiusResultError)
            }
            override fun afterTextChanged(e: Editable) {}
        })
    }
}