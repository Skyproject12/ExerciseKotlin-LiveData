package com.example.livedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

public class MainViewModel() : ViewModel() {
    companion object {
        private const val ONE_SECOND = 1000
    }

    private val mInitialTime: Long
    private val mElapseTime = MutableLiveData<Long?>()

    init {
        // call clock realtime
        mInitialTime = SystemClock.elapsedRealtime()
        val timer = Timer()
        // start the time
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime)
                // save the value of time in mutable live data
                mElapseTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }

    // get mutable live data with return result of times
    fun getElapsedTime(): LiveData<Long?> {
        return mElapseTime
    }
}