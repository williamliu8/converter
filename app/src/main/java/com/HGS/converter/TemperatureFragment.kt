package com.HGS.converter

import android.icu.util.Output
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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