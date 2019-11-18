package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(),
    AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this,"Position"+position,Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerAge.onItemSelectedListener= this
        buttonCalculate.setOnClickListener {
            calculatePremium()
        }
    }

    private fun calculatePremium(){
        val position = spinnerAge.selectedItemPosition
        val gender = radioGroupGender.checkedRadioButtonId
        val age = spinnerAge.getChildAt(position)
        var basic_premium: Int = 0
        var extra_Payment1: Int = 0
        var extra_Payment2: Int = 0
        var total_Premium: Int = 0
        basic_premium=when(position){
            0 -> 60 //less than 17
            1 -> 70 //17 to 25
            2 -> 90
            3 -> 120
            else -> 150
        }
        total_Premium+=basic_premium
        //when(position){
        //   0 -> basic_premium=60 //less than 17
        //   1 -> basic_premium=70 //17 to 25
        //    else -> basic_premium=150
        //}

        if(gender== R.id.radioButtonMale){
            extra_Payment1=when(position){
                0 -> 0 //less than 17
                1 -> 50 //17 to 25
                2 -> 100
                3 -> 150
                else -> 200
            }
        }
        total_Premium+=extra_Payment1
        if (checkBoxSmoker.isChecked){
            extra_Payment2=when(position){
                0 -> 0 //less than 17
                1 -> 100 //17 to 25
                2 -> 150
                3 -> 200
                4 -> 250
                else -> 300
            }

        }
        val symbol  = Currency.getInstance(Locale.getDefault()).symbol
        total_Premium+=extra_Payment2
        textViewInsurance.text=String.format("%s : %s %d",getString(R.string.insurance_premium),symbol,total_Premium)
    }
}
