package ru.myitacademy.samsung.personaldataquestionnaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainVModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainVModel::class.java)
        val hintEdit = resources.getStringArray(R.array.hints)
        val inputData = findViewById<EditText>(R.id.editText)
        val outputData = findViewById<TextView>(R.id.got_data)
        val next = findViewById<Button>(R.id.next)
        val new_person = findViewById<Button>(R.id.new_person)
        viewModel.questionnaire.observe(this, Observer { outputData.text = it})
        var i = viewModel.retC()
        inputData.hint = hintEdit[i]
            next.setOnClickListener {
                if(inputData.text == null || inputData.text.toString().equals("") )
                    Toast.makeText(this,"Введите данные: " + inputData.hint,Toast.LENGTH_SHORT).show()
                else{
                viewModel.allData(
                    outputData.text.toString(),
                    inputData.hint.toString(),
                    inputData.text.toString()
                )
                inputData.setText("")
                i++
                viewModel.initC(i)
                if(i>=hintEdit.size) {
                next.isClickable=false
                inputData.isCursorVisible = false
            }
            inputData.hint=if(i<hintEdit.size) hintEdit[i] else ""
            }
            }
        new_person.setOnClickListener {
            outputData.setText(resources.getText(R.string.answer))
            viewModel.reset(outputData.text.toString())
            i=viewModel.retC()
            inputData.hint=hintEdit[i]
            next.isClickable = true
            inputData.isCursorVisible = true
            inputData.setText("")
        }

    }
}