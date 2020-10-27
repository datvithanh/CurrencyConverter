package com.example.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var currencies = arrayOf("USD", "CAD", "SGD", "VND", "AUD", "EUR", "YEN", "GBP", "HKD", "CNY")
        var ratios = arrayOf(1.0, 1.318083, 1.359097, 23176.50, 1.403807, 0.845641, 0.767473, 104.543599, 7.749896, 6.706052)

        val spinner1 = findViewById(R.id.spinner) as Spinner;
        val spinner2 = findViewById(R.id.spinner2) as Spinner;
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.setAdapter(adapter)
        spinner2.setAdapter(adapter)

        val editText = findViewById<EditText>(R.id.edit_vnd)
        // globally
        // globally
        val textView = findViewById(R.id.edit_vnd2) as TextView

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                var from = spinner1.selectedItem.toString()
                var to = spinner2.selectedItem.toString()
                var index1 = currencies.indexOf(from)
                var index2 = currencies.indexOf(to)
                var result = ""
                Log.d("ratio1", ratios[index1].toString())
                Log.d("ratio2", ratios[index2].toString())

                // you can call or do what you want with your EditText here
                if (s.toString() != "") {
                    if (index1 == 0)
                        result = (s.toString().toFloat() * ratios[index2]).toString()
                    else {
                        if (index2 == 0)
                            result = (s.toString().toFloat() * (1 / ratios[index1])).toString()
                        else
                            result = (s.toString().toFloat() * ratios[index2] * (1 / ratios[index1])).toString()
                    }
                }

                textView.text = result
                Log.d("alsdl", s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}