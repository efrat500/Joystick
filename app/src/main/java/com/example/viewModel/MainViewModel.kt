package com.example.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.Model

class MainViewModel:ViewModel() {


    fun onConnect(ip : String, port: Int){
        println(ip)
        println(port)
        if(!Model.createSocket(ip,port)){
            throw Exception()
        }
        Model.connect()
    }

    fun setRudder(num:Int){
        val rudder = (num - 500) / 500.0
        //println("testRudder $rudder")
        Model.setting("rudder", rudder.toDouble())
    }
    fun setThrottle(num:Double){
        val thr = num  / 1000.0
        Model.setting("throttle", thr)
    }
    fun setElevator(num:Double){
        Model.setting("elevator", num)
    }
    fun setAileron(num:Double){
        Model.setting("aileron", num)
    }
    fun disConnect(){
        Model.stop()
    }

}