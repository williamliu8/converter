package com.HGS.converter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_temperature.*

class TemperatureFragment : Fragment() {
    //val runButton : Button = getView()?.findViewById<Button>(R.id.button_F2C) as Button
    //var runButton: Button = button_F2C
    //var CelsiusTextView : TextView = Celsius

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      //  runButton.setOnClickListener(){ TODO("not implemented")}
        return inflater.inflate(R.layout.fragment_temperature, container,false)
     }
/*
    fun temp_F2C_Fun(v:View) {

        //var addup = 0
        //addup++
        val newScore = getString(R.string.weight)
        CelsiusTextView.text = newScore
        /*
        var COutput:TextView

        val Ftemp:Float
        var Ctemp:Float

        Ftemp = 75f
        COutput = Celsius
        Ctemp = (Ftemp-32)*5/9
        */
        //Celsius.text = addup.toString()
    }*/
}