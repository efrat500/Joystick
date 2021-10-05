package com.example.joystick

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.joystick.databinding.ActivityMainBinding
import com.example.view.Joystick
import com.example.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private var myViewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main).apply{
            this.lifecycleOwner = this@MainActivity
            this.viewModel = myViewModel}
        val connect = findViewById<Button>(R.id.buttonConnect)
        connect.setOnClickListener{onConnect()}
        val disConnect = findViewById<Button>(R.id.buttonDisconnect)
        disConnect.setOnClickListener{OnDisconnect()}
        val sliderRudder = findViewById<SeekBar>(R.id.sliderRudder)
        sliderRudder.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                myViewModel.setRudder(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        val sliderThrottle = findViewById<SeekBar>(R.id.sliderThrottle)
        sliderThrottle.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                myViewModel.setThrottle(progress.toDouble())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        val myJoystick : Joystick = findViewById(R.id.Joystick)
        myJoystick.onChange = {a : Float, e : Float ->
            myViewModel.setAileron(a.toDouble())
            myViewModel.setElevator(e.toDouble())
        }

    }
    private fun onConnect(){
        val textConnect = findViewById<View>(R.id.messageText) as TextView
        var ip = findViewById<EditText>(R.id.editTextIP).text
        var port = findViewById<EditText>(R.id.editTextPort).text
        if(ip.isEmpty() || port.isEmpty()){
            //Toast.makeText(this,"ip or port missing",Toast.LENGTH_SHORT).show()
            textConnect.setTextColor(Color.RED)
            textConnect.text = "Ip or port missing"
            return
        }
        try{
            myViewModel.onConnect(ip.toString(), port.toString().toInt())
            textConnect.setTextColor(Color.BLACK)
            textConnect.text = "Connected!!"
        } catch (e : Exception){
            textConnect.setTextColor(Color.RED)
            textConnect.text = "The connection failed"
            e.printStackTrace()
        }
    }
    private  fun OnDisconnect(){
        val textDisconnect = findViewById<View>(R.id.messageText) as TextView
        myViewModel.disConnect()
        textDisconnect.setTextColor(Color.RED)
        textDisconnect.text = "Disconnect"
    }
}