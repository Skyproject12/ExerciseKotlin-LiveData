package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // initial viewmodel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        subscribe()
    }
    private fun subscribe(){
        val time = Observer<Long?> { along ->
            // set time use viewmodel with along
            val newTask = this@MainActivity.resources.getString(R.string.second, along)
            // set display with newTask
            text_hasil.text= newTask
        }
        // get time
        viewModel.getElapsedTime().observe(this, time)
    }
}
