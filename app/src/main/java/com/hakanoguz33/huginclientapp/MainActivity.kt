package com.hakanoguz33.huginclientapp

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.format.Formatter
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hakanoguz33.huginclientapp.view.SalesActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket

class Server:Runnable{
    lateinit var s:Socket
    lateinit var ss:ServerSocket
    lateinit var isr:InputStreamReader
    lateinit var buff:BufferedReader
    lateinit var message:String
    lateinit var h:Handler
    override fun run() {
        try {
            ss = ServerSocket(6060)
            while (true)
            {
                s=ss.accept()
                isr = InputStreamReader(s.getInputStream())
                buff = BufferedReader(isr)
                message = buff.readLine()

                        println(message)
                        Log.e("olay",message)

            }
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ip:TextView = findViewById(R.id.ipTextView)
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ipAddress: String = Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
        ip.text = "$ipAddress"

        var server:Server = Server()
        val tri:Thread = Thread(server)

        tri.start()
        var btn: Button = findViewById(R.id.btnConnection)

        btn.setOnClickListener {
            val intent:Intent = Intent(this,SalesActivity::class.java)
            startActivity(intent)
        }

    }
}