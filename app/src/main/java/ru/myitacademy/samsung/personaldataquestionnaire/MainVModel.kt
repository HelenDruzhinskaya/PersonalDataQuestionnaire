package ru.myitacademy.samsung.personaldataquestionnaire

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainVModel: ViewModel() {
    var questionnaire = MutableLiveData<String>()
    var c: Int = 0
    fun allData(oldData: String,inputHint: String,inputStr:String){
questionnaire.value = oldData+ "\n"+ inputHint+": "+ inputStr
    }
    fun retC(): Int{
        return c
    }
    fun initC(x: Int){ c = x}
    fun reset(s: String){
        questionnaire.value = s
        c = 0
    }
}