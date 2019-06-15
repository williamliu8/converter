package com.HGS.converter

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_popup.*
import kotlinx.android.synthetic.main.fragment_length.*
import kotlinx.android.synthetic.main.fragment_volume.*

class popupActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)

        var calnumInput:Float = 1f
        var calnumInput1:Float = 1f
        var caldenInput:Float = 1f
        var caldenInput1:Float = 1f

        fun calResult(){
            var calResult = calnumInput*calnumInput1/(caldenInput*caldenInput1)

            val calResultText:TextView = findViewById(R.id.calResult)
            calResultText.text = calResult.toString()
        }

        calnumInputtext.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var calnumInputNable = s.toString().toFloatOrNull()

                if(calnumInputNable==null){
                    calnumInputtext.hint=getString(R.string.numerator)
                    calnumInput = 1f
                }
                else{
                    calnumInput = calnumInputNable
                    calResult()
                }

            }
            override fun afterTextChanged(e: Editable) {}
        })

        calnumInputtext1.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var calnumInputNable = s.toString().toFloatOrNull()

                if(calnumInputNable==null){
                    calnumInputtext.hint=getString(R.string.numerator)
                    calnumInput1 = 1f
                }
                else{
                    calnumInput1 = calnumInputNable
                    calResult()
                }

            }
            override fun afterTextChanged(e: Editable) {}
        })

        caldenInputtext.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var caldenInputNable = s.toString().toFloatOrNull()

                if(caldenInputNable==null){
                    caldenInputtext.hint=getString(R.string.denominator)
                    caldenInput = 1f
                }
                else{
                    caldenInput = caldenInputNable
                    calResult()
                }

            }
            override fun afterTextChanged(e: Editable) {}
        })

        caldenInputtext1.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var caldenInputNable = s.toString().toFloatOrNull()

                if(caldenInputNable==null){
                    caldenInputtext1.hint=getString(R.string.denominator)
                    caldenInput1 = 1f
                }
                else{
                    caldenInput1 = caldenInputNable
                    calResult()
                }

            }
            override fun afterTextChanged(e: Editable) {}
        })



        val botton = findViewById(R.id.button) as Button
        button.setOnClickListener{
            val mainactive = Intent(this,MainActivity::class.java)
            startActivity(mainactive)
        }




    }
}
