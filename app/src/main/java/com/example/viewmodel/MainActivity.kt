package com.example.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodel.ui.theme.ViewModelTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//          var myViewModel:MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
//            var myViewModel:MyViewModel=MyViewModel(
            var res = remember{mutableStateOf("0")}
           var myViewModel:MyViewModel = viewModel()
            val checkedState = remember { mutableStateOf(true) }
            Column() {

                TextField(value = res.value, onValueChange = {res.value=it.toString()})

                Switch(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it
                     myViewModel.switchChange()
                    }
                )
                Button(onClick = {myViewModel.convertTemp(res.value)}) {
                    Text(text = "texte")
                }
                Text(text = myViewModel.result)
            }
            }
    }
}
class MyViewModel:ViewModel(){
    var isFahrenheit by mutableStateOf(true)
    var result by mutableStateOf("")
    fun convertTemp(temp: String) {

        try {
            val tempInt = temp.toInt()

            if (isFahrenheit) {
                result = ((tempInt - 32) * 0.5556).roundToInt().toString()
            } else {
                result = ((tempInt * 1.8) + 32).roundToInt().toString()
            }
        } catch (e: Exception) {
            result = "Invalid Entry"
        }

    }
    fun switchChange() {
        isFahrenheit = !isFahrenheit
    }
}

