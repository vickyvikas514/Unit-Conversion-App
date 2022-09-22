package com.example.unitconversion

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import com.example.unitconversion.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.Double

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView2.setOnClickListener { calcconv() }
        binding.yourReadingEditText.setOnKeyListener { view, keyCode, _ ->
            handlekey(view, keyCode)
        }
    }
    private fun handlekey(view: View,keycode:Int):Boolean{
        if(keycode== KeyEvent.KEYCODE_ENTER){
            val inputMethodManager=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
            return true
        }
        return false
    }

    private fun calcconv() {

        val stringinput=binding.yourReadingEditText.text.toString()
        val inputvalue=stringinput.toDouble()
        if(inputvalue==null){
            binding.result.text=""
        }
        val selectedid=binding.chooseOne.checkedRadioButtonId
        val choosenval=when(selectedid){
            R.id.miligram->0.001
            R.id.kilogram->1000.0
            R.id.ounce->28.349
            else->453.59
        }
        val finalinputvalue= inputvalue*(choosenval)

        val selectedid_2=binding.chooseTwo.checkedRadioButtonId
        val choosenval_2=when(selectedid_2){
            R.id.miligram_1->1000.0
            R.id.kilogram_1->0.001
            R.id.ounce_1->0.03527
            else->0.002204
        }
        var finaloutputvalue= finalinputvalue*choosenval_2
        if((selectedid==R.id.ounce&& selectedid_2==R.id.ounce_1)||(selectedid==R.id.kilogram && selectedid_2==R.id.kilogram_1)||(selectedid==R.id.pound&&selectedid_2==R.id.pound_1)){
            finaloutputvalue=inputvalue
        }
        val roundoff=binding.switch1.isChecked
        if(roundoff){
            finaloutputvalue=kotlin.math.ceil(finaloutputvalue)
        }
        val format_1=NumberFormat.getNumberInstance().format(finaloutputvalue)
        binding.result.text=format_1
    }
}




