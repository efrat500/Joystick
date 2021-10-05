package com.example.model

import java.io.PrintWriter
import java.lang.Exception
import java.net.Socket
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingDeque

object Model {
    private var mySocket: Socket? = null
    private var writer: PrintWriter? = null
    private var queue : BlockingQueue<Runnable> = LinkedBlockingDeque()
    private var isConnected = false
    fun createSocket(ip:String?,port:Int):Boolean{
        val t = Thread {
            try {
            mySocket = Socket(ip, port)
            writer = PrintWriter(mySocket!!.getOutputStream(), true)
            isConnected = true
        }
        catch (e:Exception){
            println("catch")
        }
        }
        t.start()
        t.join()
        return isConnected
    }

    fun connect(){
        Thread(Runnable {
            while (isConnected){
                try{
                    println("in model")
                    queue.take().run()
                }
                catch(e:InterruptedException){}
            }
        }).start()
    }

    fun stop(){
        mySocket?.close()
        writer?.close()
        isConnected = false
    }

    fun setting(data:String, num: Double){
        var toPrint: String?= null
        if(data =="rudder"){
            println("in setting")
            toPrint = "set /controls/flight/rudder $num\r\n"
        }
        else if(data == "throttle"){
            toPrint = "set /controls/engines/current-engine/throttle $num\r\n"
        }
        else if(data == "aileron"){
            toPrint = "set /controls/flight/aileron $num\r\n"
        }
        else if(data == "elevator"){
            toPrint = "set /controls/flight/elevator $num\r\n"
        }
        println(toPrint)
        queue.put(Runnable {
            writer?.write(toPrint)
            writer?.flush()
        })
    }
}