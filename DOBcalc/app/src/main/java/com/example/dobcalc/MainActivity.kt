package com.example.dobcalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var tvSelecteddate : TextView? = null
    private var tvageinmin : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btndatepicker: Button = findViewById(R.id.bt1)
        tvSelecteddate = findViewById(R.id.tvSelecteddate)
        tvageinmin = findViewById(R.id.tvageinmin)

        btndatepicker.setOnClickListener {
            clickdatepicker()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    @SuppressLint("SuspiciousIndentation")
    fun clickdatepicker(){
        val MyCalender = Calendar.getInstance()
        val year  = MyCalender.get(Calendar.YEAR)
        val month = MyCalender.get(Calendar.MONTH)
        val day = MyCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedyear, selectedmonth, selecteddayOfMonth ->
                Toast.makeText(this,"Year was $year, Month was ${month+1}, Day was $selecteddayOfMonth", Toast.LENGTH_SHORT).show()
                val selecteddate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
                tvSelecteddate?.text = selecteddate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
                val theDate = sdf.parse(selecteddate)

                val selcteddateinmin = theDate.time / 60000
                val currentdate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateinmin = currentdate.time / 60000
                val diffinmin = currentDateinmin - selcteddateinmin

                tvageinmin?.text = diffinmin.toString()
            },
            year,
            month,
            day
        )

            dpd.show()

    }
}