package com.example.mycalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var tvinput: TextView? = null
    var lastnumeric: Boolean = false
    var lastdot: Boolean = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvinput = findViewById(R.id.tvinput)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // This should be outside onCreate to work as an XML onClick function
    fun onDigit(view: View) {
        tvinput?.append((view as Button).text)
        lastnumeric = true
        lastdot  = false
    }

    fun onClear(view: View) {
        tvinput?.text = ""
    }

    fun onDecimalPoint(view: View) {
        if(lastnumeric && !lastdot){
            tvinput?.append(".")
            lastnumeric = false
            lastdot = true
        }
    }

    fun onEqual(view: View){
        if(lastnumeric){
            var tvvalue = tvinput?.text.toString()
            var prefix = ""

            try {
                if(tvvalue.startsWith("-")){
                    prefix = "-"
                    tvvalue = tvvalue.substring(1)
                }
                if(tvvalue.contains("-")){
                    val splitvalue = tvvalue.split("-")

                    var one = splitvalue[0]//99
                    var two = splitvalue[1]//1

                    tvinput?.text = removezeroafterdot ((one.toDouble() - two.toDouble()).toString())
                }else if(tvvalue.contains("+")){
                    val splitvalue = tvvalue.split("+")

                    var one = splitvalue[0]//99
                    var two = splitvalue[1]//1

                    tvinput?.text = removezeroafterdot ((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvvalue.contains("*")){
                    val splitvalue = tvvalue.split("*")

                    var one = splitvalue[0]//99
                    var two = splitvalue[1]//1

                    tvinput?.text = removezeroafterdot ((one.toDouble() * two.toDouble()).toString())
                }
                else if(tvvalue.contains("/")){
                    val splitvalue = tvvalue.split("/")

                    var one = splitvalue[0]//99
                    var two = splitvalue[1]//1

                    tvinput?.text =removezeroafterdot ((one.toDouble() / two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }

    }

    fun removezeroafterdot(result: String) : String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0,result.length-2)
        }
        return value
    }

    fun onOperator(view: View) {
        tvinput?.text?.let {
            if(lastnumeric && !isOperatorAdded(it.toString())){
                tvinput?.append((view as Button).text)
                lastnumeric = false
                lastdot = false
            }
        }
    }

    private fun isOperatorAdded(value: String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-")
        }
    }
}
